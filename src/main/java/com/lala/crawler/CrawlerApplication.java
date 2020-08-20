package com.lala.crawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CrawlerApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(CrawlerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        String html = "<html><head><title>Website title</title></head><body><p>Sample paragraph number 1 </p><p>Sample paragraph number 2</p></body></html>";

        Document doc = Jsoup.connect("https://www.flipkart.com/mobiles-accessories/pr?sid=tyy").get();//mobile page
        String[] pages=doc.getElementsByClass("_2zg3yZ").select("span").text().split(" "); //number of pages
        String endPage= pages[3].replaceAll(",",""); //last page ex- 6,200
        Integer noOfPages=Integer.parseInt(endPage); //remove comma from last page
        for(int i=0;i<1;i++){
            Document doc1 = Jsoup.connect("https://www.flipkart.com/mobiles/pr?sid=tyy%2C4io&page="+i+"").get();
          //  _1UoZlX

            Elements images = doc1.getElementsByClass("_3SQWE6"); //div element containing all details
            Elements items = doc1.getElementsByClass("_1-2Iqu"); //div element containing all details
            int count=0;
            for (Element item : items) {
                System.out.println(count+1);
                System.out.println("Product Description: "+ item.getElementsByClass("_3wU53n").text());
                System.out.println("Sale Price: "+item.getElementsByClass("_1vC4OE").text());
                System.out.println("Original Price: "+item.getElementsByClass("_3auQ3N").text());
                System.out.println("Discount percentage: "+item.getElementsByClass("VGWI6T").text());
            }
            System.out.println("-------------------------------------------");
        }



    }
}

