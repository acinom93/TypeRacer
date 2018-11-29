package monash.sydney.edu.au.typeracing;

class CheckBoxItem {
    String itemName;
    boolean isChecked;

    public CheckBoxItem(String itemName, boolean isChecked) {
        this.itemName = itemName;
        this.isChecked = isChecked;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
