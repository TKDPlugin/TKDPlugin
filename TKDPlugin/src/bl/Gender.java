package bl;

public enum Gender {
    male,female;
    
    public String toChinese() {
        switch (this) {
        case male:
            return "男";
          
        case female:
            return "女";
        }
        return null;
    }
}
