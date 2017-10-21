from [course](https://www.coursera.org/learn/cryptocurrency)

# blockchain notes

- key 256 bits
- hash 256 bits
- hash function: 2^130 inputs - 99% chance that 2 of them will collide
- hash is small	
- hiding property : cannot find message given hash, if msg is has high min-entropy

## commitment

to write:
commitment, key = commit(msg)
publish commitment

to read:
publish key, msg
match = verify(commitment, key, msg)

hiding property :
binding property : not possible to find msg' such as verify(commitment, key, msg') = true
puzzle friendly : given H(key, msg), and if key randomized 256 bit value, then its not possible to find msg' such that H(key, msg') = H(key, msg). Here key is public key. 

## how SHA works

split the message into blocks of equal size - 512 bits
padding is added in last block (also adds the length bit)

comp_fn is the compression function
o1 = comp_fn(iv, block1)
o2 = comp_fn(o1, block2)
.
.
hash = comp_fn(o(n-1), block_n)

## Hash Pointer
pointer to where some data is stored + cryptographic hash of that data
lets us verify if the data has been tampered
Merkle tree - binary tree made of hash pointers. 
			- each node has two hash pointer - left, right
			- root has the data
			- we hold the root hash pointer
			- to validate if some data is in the tree, provided the subtree leading to that data, it can be done in log_n time
Sorted merkel tree
		    - the nodes in the leaf are sorted
		    - can verify non-membership by showing item before and after that member

## Digital signatures
only the author can sign
every one else can verify that it's your signature
sign is tied to a document(cant copy a sign from one doc to another)

(sk, pk) = generateKeys(keysize)
sk = secret key
pk = public key

signature = sign(sk, message)
isValid = verify(signature, pk, message)

- cant forge signature

use: if hashpointer end of blockchain is signed, then its is equal to signing the entire blockchain

Bitcoin uses ECDSA signature algorithm.

## Public key as identity

if verify("hii", pk, signature) == true ("hii" is the message here)
then we can be sure that the "hii" came from pk (the person who owns the sk of pk). => pk says "hii"

## Goofycoin

1) goofy can create new coins  [goofy's signature| create coin[coin id]]
any one can verify goofy's signature
2) goofy can send the coin to any one and then can spend it
[goofy's sign| pay to Pk of Alice: H()] <-  [goofy's signature| create coin[coin id]]
3) Alice can now spend this coin
[Alice's sign| pay to Pk of Bob: H()] <- [goofy's sign| pay to Pk of Alice: H()] <-  [goofy's signature| create coin[coin id]]

double spending problem - alice can spend the same coin to multiple people

## Scrooge coin

Scrooge keeps a history of transaction - blockchain and signed by Scrooge
[prev H(), tx_id | tran] <- [prev H(), tx_id | tran] <- H() (held by Scrooge)

createCoin transaction
list of coins
	- num, value, recipient(pk of who gets the coin)
	- every coin has id tx_coinid

paycoin txn
	- has consumed coins (coin ids)
	- list of create coins
		- sum should add up to consumed
		- recipient could be same person or some other person's pk
	- signatures
		- by each pk who's coin is being consumed

dis advantage - central txn history

## Distributed Consensus - in bitcoin

- a network of bitcoin nodes

### How bitcoin consensus would have worked if we use traditional consensus algms:

	- uses consensus algorithm to agree upon a transaction history
	- each node has this txn sequence(blocks of txns) - that they have reached a consensus on
	- each node can have a set of txns that it has heard, but is not yet broadcasted to other nodes
	- each node sends its block to all other nodes - and one block is chosen and then added to the chain
### why traditional algms dont work:

	- nodes may crash
	- malicious node
	- not all nodes are connected
	- faulty network
	- latency
	- no global time
	- byzantine general's problem
	- fisher lynch paterson impossibility problem

### How bitcoin solves the problem:

	- introduces incentives
	- consensus happens over long time
	- no long term identity in bitcoin
		- having identity can cause sybil attack
		
### bitcoin's consensus algm:
	- alice boradcast that she made a payement to bob - to all nodes in the network.	
	- each node updates its block with this transaction
	- in each round, a random node is selected to broadcast it's blocks
	- other nodes validates each txn in this block
	- if found valid, it adds the blocks hash as its next block
attacks
	- alice try to steal another bob's coin - not possible since cannot forge bob's signature
	- alice try to deny service to bob by not including that txn. - in the next round alice will not be chosen(randomly) to broadcast, then bob's txn will make it in the blockchain.
	- alice try to spend a coin to bob and Alice'(owned by alice)
		- if the txn from alice -> alice' gets added to the chain, this also => bob lost the coin, since that tnx did not make it to the chain. - orphan block
	(Honest node always extends longest chain)
		- to prevent double spend, Bob should wait for more confirmation from other nodes before completing the txn.