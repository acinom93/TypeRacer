package monash.sydney.edu.au.typeracing;

import android.os.Parcel;
import android.os.Parcelable;

public class TextItem implements Parcelable {

    String text;
    String textWithImage;
    String bookname;
    String amazonlink;

    public TextItem( String bookname, String amazonlink ,String text, String textWithImage) {
        this.text = text;
        this.textWithImage = textWithImage;
        this.bookname = bookname;
        this.amazonlink = amazonlink;
    }

    protected TextItem(Parcel in) {
        text = in.readString();
        textWithImage = in.readString();
        bookname = in.readString();
        amazonlink = in.readString();
    }

    public static final Creator<TextItem> CREATOR = new Creator<TextItem>() {
        @Override
        public TextItem createFromParcel(Parcel in) {
            return new TextItem(in);
        }

        @Override
        public TextItem[] newArray(int size) {
            return new TextItem[size];
        }
    };

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTextWithImage() {
        return textWithImage;
    }

    public void setTextWithImage(String textWithImage) {
        this.textWithImage = textWithImage;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAmazonlink() {
        return amazonlink;
    }

    public void setAmazonlink(String amazonlink) {
        this.amazonlink = amazonlink;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(textWithImage);
        dest.writeString(bookname);
        dest.writeString(amazonlink);
    }
}
