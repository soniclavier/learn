import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TxHandler {

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    private UTXOPool utxoPool;

    public TxHandler(UTXOPool utxoPool) {
        this.utxoPool = new UTXOPool(utxoPool);
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        double totalOutput = 0.0;
        for(int i = 0; i < tx.getOutputs().size(); i++) {
            UTXO utxo = new UTXO(tx.getHash(), i);
            if (utxoPool.contains(utxo)) {
                return false;
            }
            Transaction.Output output = tx.getOutput(i);
            if (output.value < 0) {
                return false;
            } else {
                totalOutput += output.value;
            }
        }

        Set<UTXO> utxos = new HashSet<UTXO>();
        double totalInput = 0.0;
        for(int i = 0; i < tx.getInputs().size(); i++) {
            Transaction.Input in = tx.getInput(i);
            UTXO prevUtxo = new UTXO(in.prevTxHash, in.outputIndex);
            if (utxos.contains(prevUtxo)) {
                return false;
            } else {
                utxos.add(prevUtxo);
            }
            if (!utxoPool.contains(prevUtxo)) {
                return false;
            }
            Transaction.Output prevOutput = utxoPool.getTxOutput(prevUtxo);
            if (!Crypto.verifySignature(prevOutput.address, tx.getRawDataToSign(i), in.signature)) {
                return false;
            }
            totalInput += prevOutput.value;
        }

        if (totalInput < totalOutput) {
            return false;
        }
        return true;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        Set<UTXO> usedUtxo = new HashSet<UTXO>();
        List<Transaction> validTxList = new ArrayList<Transaction>();

        for(Transaction tx : possibleTxs) {
            if (isValidTx(tx)) {
                validTxList.add(tx);
                for(int i = 0; i < tx.getOutputs().size(); i++) {
                    utxoPool.addUTXO(new UTXO(tx.getHash(), i), tx.getOutput(i));
                }
            }
        }
        Transaction[] validTxs = new Transaction[validTxList.size()];
        for(int i = 0; i < validTxList.size(); i++) {
            validTxs[i] = validTxList.get(i);
        }
        return validTxs;
    }

}
