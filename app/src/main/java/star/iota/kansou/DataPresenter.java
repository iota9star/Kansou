package star.iota.kansou;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;


class DataPresenter extends StringPresenter {

    DataPresenter(PVContract.View view) {
        super(view);
    }

    @Override
    protected Bean dealResponse(int type, Document document) {
        HashMap<String, ArrayList<Bean.ItemBean>> category = new HashMap<>();
        ArrayList<String> titles = new ArrayList<>();
        for (Element element : document.select("#contents > h2")) {
            titles.add(element.text());
        }
        Elements tables = document.select("#contents > table.list");
        tables.select("img").remove();
        for (int i = 0; i < tables.size(); i++) {
            Elements eles = tables.get(i).select("tbody > tr");
            ArrayList<Bean.ItemBean> items = new ArrayList<>();
            for (Element ele : eles) {
                Elements tds = ele.select("td");
                if (tds == null || tds.size() == 0) continue;
                Bean.ItemBean bean = new Bean.ItemBean(
                        ele.select("td:nth-child(1)") == null ? "" : ele.select("td:nth-child(1)").text(),
                        ele.select("td:nth-child(2)") == null ? "" : ele.select("td:nth-child(2)").html(),
                        ele.select("td:nth-child(3)") == null ? "" : ele.select("td:nth-child(3)").html()
                );
                items.add(bean);
            }
            category.put(titles.get(i), items);
        }
        return new Bean(type, category);
    }
}
