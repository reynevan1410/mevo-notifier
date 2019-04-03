package pl.gdansk.niedzwiednik.kuba;

import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageParser {

    @SneakyThrows(IOException.class)
    public List<StationInfo> parse(final String url) {
        final Document document = Jsoup.connect(url).get();
        final Elements tableRows = document.getElementsByTag("table")
                .get(0)
                .getElementsByTag("tr");
        final List<StationInfo> stationInfos = new ArrayList<>();
        for (final Element element : tableRows) {
            if (isNotHeader(element)) {
                final Elements columns = element.getElementsByTag("td");
                final String station = columns.get(0).text();
                final String availableBikesNumber = columns.get(1).text();
                final String reservedBikes = columns.get(4).text();
                final String reservedBikesNumber = columns.get(5).text();
                stationInfos.add(
                        new StationInfo(
                                Integer.valueOf(station),
                                Integer.valueOf(availableBikesNumber),
                                reservedBikes,
                                Integer.valueOf(reservedBikesNumber))
                );
            }
        }
        return stationInfos;
    }

    private boolean isNotHeader(final Element element) {
        return element.getElementsByTag("th").isEmpty();
    }
}
