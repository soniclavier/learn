import java.util.ArrayList;
import java.util.List;

/**
 * Created by vishnu on 6/5/16.
 */
public class TextJustification {

    public static void main(String[] args) {
        SolutionTextJust s = new SolutionTextJust();
        //System.out.println(s.fullJustify(new String[]{"What","must","be","shall","be."},12));
        List<String> test = new ArrayList<>();
        test.add("so");
        test.add("fine");
        test.add("That");
        test.add("all");
        test.add("the");
        System.out.println(s.justify(test,25,false));

    }
}

class SolutionTextJust {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();

        int tempSum = 0;
        List<String> temp = new ArrayList<>();
        for(int i=0;i<words.length;i++) {
            int sp = 0;
            if (i > 0)
                sp = 1;
            if (tempSum + sp + words[i].length() > maxWidth) {
                res.add(justify(temp,maxWidth,false));
                temp.clear();
                temp.add(words[i]);
                tempSum = words[i].length();
            } else {
                temp.add(words[i]);
                tempSum += words[i].length()+sp;
            }
        }
        if (temp.size() > 0)
            res.add(justify(temp,maxWidth,true));
        return res;
    }

    public String justify(List<String> words,int maxWidth,boolean last) {
        int sp = words.size()-1;

        int l = 0;
        for(String word : words) l+= word.length();
        int diff = maxWidth-(l+sp);
        if (last) {
            String s = "";
            for(int i=0;i<words.size();i++) {
                s += words.get(i);
                if (i != words.size()-1)
                    s+=" ";
            }
            while(diff > 0) {
                s += " ";
                diff --;
            }
            return s;
        }
        int perSpace = 0;
        if (sp == 0)
            perSpace = diff;
        else
            perSpace = diff/sp;


        String spaceStr = "";
        for(int i=0;i<perSpace;i++)
            spaceStr += " ";

        if (sp == 0) {
            return words.get(0) + spaceStr;
        }
        int diff2 = diff % sp;
        String res = "";
        for(int i=0;i<words.size();i++) {
            res += words.get(i);
            if (i != words.size()-1) {
                res += " "+spaceStr;
                diff -= perSpace;
                if (diff2 > 0) {
                    res += " ";
                    diff2--;
                }

            }
        }
        return res;
    }
}
