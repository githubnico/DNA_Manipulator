/**
 * Created by Deviltech on 03.11.2015.
 */
public class Sequence {

    private String mySequence;

    public Sequence(String mySequence) {
        this.mySequence = mySequence;
    }

    public String getMySequence() {
        return mySequence;
    }

    public void setMySequence(String mySequence) {
        this.mySequence = mySequence;
    }

    public String filter(){
        return mySequence.replaceAll("[^ATGCUatgcu]+", "");
    }

    public String toUpper(){
        return mySequence.toUpperCase();
    }

    public String toLower(){
        return mySequence.toLowerCase();
    }

    public String toRNA(boolean isRNA){
        String result = "";
        if(isRNA){
        for (int i = 0; i < mySequence.length(); i++) {
            switch(mySequence.charAt(i)){
                case 't' : result += 'u';
                    break;
                case 'T' : result += 'U';
                    break;
                default: result += mySequence.charAt(i);
            }
        }} else{
            for (int i = 0; i < mySequence.length(); i++) {
                switch(mySequence.charAt(i)){
                    case 'u' : result += 't';
                        break;
                    case 'U' : result += 'T';
                        break;
                    default: result += mySequence.charAt(i);
                }
            }
        }
        return result;
    }

    public String reverse(){
        return new StringBuilder(mySequence).reverse().toString();
    }

    public String complementary(boolean isRNA) {
        String result = "";
            for (int i = 0; i < mySequence.length(); i++) {
                switch (mySequence.charAt(i)) {
                    case 'g':
                        result += 'c';
                        break;
                    case 'G':
                        result += 'c';
                        break;
                    case 'c':
                        result += 'g';
                        break;
                    case 'C':
                        result += 'G';
                        break;
                    case 't':
                        result += 'a';
                        break;
                    case 'T':
                        result += 'A';
                        break;
                    case 'u':
                        result += 'a';
                        break;
                    case 'U':
                        result += 'A';
                        break;
                    case 'a':
                        if(isRNA){
                            result += 'u';
                        } else {
                            result += 't';
                        }
                        break;
                    case 'A':
                        if(isRNA){
                            result += 'U';
                        } else {
                            result += 'T';
                        }
                        break;
                    default:
                        result += mySequence.charAt(i);
                }
            }
        return result;
    }

    public String reverseComplementary(boolean isRNA){
        return new Sequence(new Sequence(mySequence).reverse()).complementary(isRNA);
    }

    public String GCContent(){
        String gc = mySequence.replaceAll("[^GgCC]+", "");
        if(gc.length()>0){
            Double result = new Double(gc.length())/new Double(mySequence.length());
            return String.valueOf(result*100) + "%";
        } else{
            return "0%";
        }
    }
    public String length(){
        return String.valueOf(mySequence.length());
    }

}
