package org.example.moviesystem.service;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import org.example.moviesystem.model.RatedSeries;
import org.example.moviesystem.model.RatedSeriesList;
import org.example.moviesystem.response.RatedResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;


@Service
public class SeriesRatedService
{
    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    public SeriesRatedService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Helper method to convert a list of RatedSeries to XML
    public Document convertListToXml(List<RatedSeries> seriesList) {
        try {
            RatedSeriesList wrapper = new RatedSeriesList();
            wrapper.setSeries(seriesList);

            JAXBContext context = JAXBContext.newInstance(RatedSeriesList.class);
            Marshaller marshaller = context.createMarshaller();

            // Convert to a StringWriter first
            StringWriter writer = new StringWriter();
            marshaller.marshal(wrapper, writer);
            String xmlString = writer.toString();

            // Parse the string into a Document object
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            return builder.parse(new InputSource(new StringReader(xmlString)));
        } catch (JAXBException | ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException("Error converting to XML Document", e);
        }
    }

    // Fetch top-rated TV series and return as XML
    public Document getTopRatedTVSeriesAsXml() {
        List<RatedSeries> seriesList = getTopRatedTVSeries(); // Fetch the list
        return convertListToXml(seriesList); // Convert to XML
    }

    // Fetch top-rated TV series from TMDB
    public List<RatedSeries> getTopRatedTVSeries() {
        String url = apiUrl + "/tv/top_rated?api_key=" + apiKey + "&language=en-US&page=1"; // Fetch 20 top-rated TV series from page 1
        RatedResponse response = restTemplate.getForObject(url, RatedResponse.class);
        return response != null ? Arrays.asList(response.getResults()) : List.of();
    }


}
//The SeriesRatedService class interacts with the TMDB API to retrieve top-rated series.