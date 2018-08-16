package service;

import model.CsvFileInfo;
import model.Customer;
import org.springframework.stereotype.Service;
import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@Service
public class CsvFileConverterService {

    private static final String DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/";
    private static final String FILE_TYPE = ".csv";
    private static Long uniqueFileNameCounter = 201808141059l;

    public CsvFileConverterService(){}

    public CsvFileInfo convertToFile(Customer customer) throws IOException {
        uniqueFileNameCounter++;
        File csvFile = new File(DIRECTORY + uniqueFileNameCounter + FILE_TYPE);
        ObjectWriter writer = new CsvMapper().writerWithTypedSchemaFor(Customer.class);
        writer.writeValues(csvFile).write(customer);

        return new CsvFileInfo(uniqueFileNameCounter.toString());
    }

    public Customer convertToCustomer(String fileName) throws IOException {
        File csvFile = new File(DIRECTORY + fileName + FILE_TYPE);
        return new CsvMapper().readerWithTypedSchemaFor(Customer.class).readValue(csvFile);
    }


}
