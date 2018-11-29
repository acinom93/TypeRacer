package monash.sydney.edu.au.typeracing;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParagraphFactory {
    static ParagraphFactory paragraphFactory;
    List<TextItem> textItems;

    private ParagraphFactory() {
        textItems = new ArrayList<>();
        init();
    }

    static ParagraphFactory getInstance() {
        if (paragraphFactory == null)
            paragraphFactory = new ParagraphFactory();

        return paragraphFactory;

    }

    private void init() {
        /*textItems.add(new TextItem("The Meltdown (Diary of a Wimpy Kid Book 13)","https://www.amazon.com/gp/product/1419727435/ref=s9_acsd_top_hd_bw_b2oXDyV_c_x_w?pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-5&pf_rd_r=8AF7PWR6AK6FTP2XAZDV&pf_rd_r=8AF7PWR6AK6FTP2XAZDV&pf_rd_t=101&pf_rd_p=b68c8f3c-8b51-54ef-8956-4762ae05eefa&pf_rd_p=b68c8f3c-8b51-54ef-8956-4762ae05eefa&pf_rd_i=2579001011",
                "There was cat on tree.",
                "There was IMG[cat] on tree."));
        textItems.add(new TextItem("The Meltdown (Diary of a Wimpy Fox Book 13)","https://www.amazon.com/gp/product/1419727435/ref=s9_acsd_top_hd_bw_b2oXDyV_c_x_w?pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-5&pf_rd_r=8AF7PWR6AK6FTP2XAZDV&pf_rd_r=8AF7PWR6AK6FTP2XAZDV&pf_rd_t=101&pf_rd_p=b68c8f3c-8b51-54ef-8956-4762ae05eefa&pf_rd_p=b68c8f3c-8b51-54ef-8956-4762ae05eefa&pf_rd_i=2579001011",
                "There was fox on tree.",
                "There was IMG[fox] on tree."));
        textItems.add(new TextItem("The Meltdown (Diary of a Wimpy dog Book 13)","https://www.amazon.com/gp/product/1419727435/ref=s9_acsd_top_hd_bw_b2oXDyV_c_x_w?pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-5&pf_rd_r=8AF7PWR6AK6FTP2XAZDV&pf_rd_r=8AF7PWR6AK6FTP2XAZDV&pf_rd_t=101&pf_rd_p=b68c8f3c-8b51-54ef-8956-4762ae05eefa&pf_rd_p=b68c8f3c-8b51-54ef-8956-4762ae05eefa&pf_rd_i=2579001011",
                "There was dog on tree.",
                "There was IMG[dog] on tree."));*/
        textItems.add(new TextItem("The Wonky Donkey","https://www.amazon.com/gp/product/0545261244/ref=s9_acsd_top_hd_bw_bjU_c_x_1_w?pf_rd_m=ATVPDKIKX0DER&pf_rd_s=merchandised-search-4&pf_rd_r=Z75BP21PBPWR18WFZH27&pf_rd_r=Z75BP21PBPWR18WFZH27&pf_rd_t=101&pf_rd_p=43724e62-803b-5b5d-ae31-5401e1a3a423&pf_rd_p=43724e62-803b-5b5d-ae31-5401e1a3a423&pf_rd_i=2820",
                "I was walking down the road and I saw. A donkey Hee Haw! And he only had three legs! He was a wonky donkey always.",
                "I was walking down the road and I saw. A IMG[donkey] Hee Haw! And he only had three legs! He was a wonky IMG[donkey] always."));
        textItems.add(new TextItem("The Pout-Pout Fish","https://www.amazon.com/Pout-Pout-Fish-Deborah-Diesen/dp/0374360979/ref=pd_bxgy_14_img_3?_encoding=UTF8&pd_rd_i=0374360979&pd_rd_r=ff635eeb-d4ed-11e8-9755-ef1de137644a&pd_rd_w=AvCxr&pd_rd_wg=FQvwX&pf_rd_i=desktop-dp-sims&pf_rd_m=ATVPDKIKX0DER&pf_rd_p=6725dbd6-9917-451d-beba-16af7874e407&pf_rd_r=ASQK4CDWY7T5B6Q5ZRGN&pf_rd_s=desktop-dp-sims&pf_rd_t=40701&psc=1&refRID=ASQK4CDWY7T5B6Q5ZRGN",
                "Deep in the water, Mr. Fish swims about. With his fish face stuck in a permanent pout. Can his pals cheer him up? Will his pout ever end?",
                "Deep in the water, Mr. IMG[fish] swims about. With his IMG[fish] face stuck in a permanent pout. Can his pals cheer him up? Will his pout ever end?"));
        textItems.add(new TextItem("Giraffes Can't Dance","https://www.amazon.com/Giraffes-Cant-Dance-Giles-Andreae/dp/0545392551/ref=pd_bxgy_14_img_2?_encoding=UTF8&pd_rd_i=0545392551&pd_rd_r=cd4cbab5-d4ef-11e8-87fb-c3725e026f8e&pd_rd_w=oqChH&pd_rd_wg=7NchD&pf_rd_i=desktop-dp-sims&pf_rd_m=ATVPDKIKX0DER&pf_rd_p=6725dbd6-9917-451d-beba-16af7874e407&pf_rd_r=HWD61EVGKADKZRFG1XED&pf_rd_s=desktop-dp-sims&pf_rd_t=40701&psc=1&refRID=HWD61EVGKADKZRFG1XED",
                "Gerald the giraffe who wants nothing more than to dance. With crooked knees and thin legs, it is harder for a giraffe than you would think. One day, Gerald is finally able to dance to his own tune in the forest.",
                "Gerald the IMG[giraffe] who wants nothing more than to dance. With crooked knees and thin legs, it is harder for a IMG[giraffe] than you would think. One day, Gerald is finally able to dance to his own IMG[tune] in the forest."));
        textItems.add(new TextItem("The Hiccupotamus","https://www.amazon.com/Hiccupotamus-Aaron-Zenz/dp/0761456228/ref=pd_sim_14_20?_encoding=UTF8&pd_rd_i=0761456228&pd_rd_r=e66a7a7e-d4ef-11e8-bb24-b147f0633e7b&pd_rd_w=cuNv9&pd_rd_wg=C6xRj&pf_rd_i=desktop-dp-sims&pf_rd_m=ATVPDKIKX0DER&pf_rd_p=18bb0b78-4200-49b9-ac91-f141d61a1780&pf_rd_r=RF4VGH5KT1TVA51S92RX&pf_rd_s=desktop-dp-sims&pf_rd_t=40701&psc=1&refRID=RF4VGH5KT1TVA51S92RX",
                "There was a hippopotamus who hiccupped quite a lotamus. And every time he got'emus, he would fall upon his bottomus!",
                "There was a IMG[hippopotamus] who hiccupped quite a lotamus. And every time he got'emus, he would fall upon his bottomus!"));
        textItems.add(new TextItem("Dragons Love Tacos","https://www.amazon.com/Dragons-Love-Tacos-Adam-Rubin/dp/0803736800/ref=pd_sim_14_15?_encoding=UTF8&pd_rd_i=0803736800&pd_rd_r=cb2773a1-d4f0-11e8-9375-534f4a4fe1de&pd_rd_w=efnEG&pd_rd_wg=xvSrQ&pf_rd_i=desktop-dp-sims&pf_rd_m=ATVPDKIKX0DER&pf_rd_p=18bb0b78-4200-49b9-ac91-f141d61a1780&pf_rd_r=CXKATPB4QYVZED15MEYG&pf_rd_s=desktop-dp-sims&pf_rd_t=40701&psc=1&refRID=CXKATPB4QYVZED15MEYG",
                "Dragons love tacos. So if you want to lure a bunch of dragons to your party, you should definitely serve tacos. And if a dragon accidentally eats spicy salsa, oh, boy. You're in fire trouble.",
                "Dragons love tacos. So if you want to lure a bunch of dragons to your party, you should definitely serve tacos. And if a IMG[dragon] accidentally eats spicy salsa, oh, boy. You're in IMG[fire] trouble."));
    }

    public TextItem getRandomTextItem() {
        Random rnd = new Random();
        return textItems.get(rnd.nextInt(textItems.size()-1));
    }
}
