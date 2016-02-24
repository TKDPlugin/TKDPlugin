package bl;

public class KGLevel implements Comparable<KGLevel> {
    private int kg;
    
    private boolean greater;

    public int getKg() {
        return kg;
    }

    public void setKg(int kg) {
        this.kg = kg;
    }

    public boolean isGreater() {
        return greater;
    }

    public void setGreater(boolean greater) {
        this.greater = greater;
    }

   
    public int compareTo(KGLevel o) {
        // TODO Auto-generated method stub
        if(kg!=o.kg) {
        	return Integer.valueOf(kg).compareTo(Integer.valueOf(o.kg));
            
        }else {
        	return Boolean.valueOf(greater).compareTo(Boolean.valueOf(o.greater));
        }
    }
    
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        String result = kg+"KG";
        if(isGreater()) {
            result = "+"+result;
        }
        return result;
    }

}
