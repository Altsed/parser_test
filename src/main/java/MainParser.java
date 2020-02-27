import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;


public class MainParser {
    public static void main(String[] args) {

        Document doc;
        try {

            //get all images from page 2 till 27
            for (int i = 2; i < 27; i++){
                String url = "https://efapel.by/product-category/rozetki-i-vyklyuchateli/litsevye-paneli-nakladki-klavishi/page/"
                        + i + "/?swoof=1&pa_collection=logus90&really_curr_tax=1607-product_cat";
            doc = Jsoup.connect(url).get();
            Elements images = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]");

            for (Element image : images) {
                String fileName = (image.attr("abs:src")).substring(image.attr("abs:src").lastIndexOf("/"));
                Response resultImageResponse = Jsoup.connect(image.attr("abs:src"))
                        .ignoreContentType(true).execute();

                FileOutputStream out = new FileOutputStream (new java.io.File("d:/temp" + fileName));
                out.write(resultImageResponse.bodyAsBytes());

                System.out.println("\nsrc : " + image.attr("src"));
                //System.out.println("height : " + image.attr("height"));
                //System.out.println("width : " + image.attr("width"));
                //System.out.println("alt : " + image.attr("alt"));

            }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
