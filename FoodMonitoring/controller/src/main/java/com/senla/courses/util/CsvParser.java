package com.senla.courses.util;

import com.opencsv.bean.CsvToBeanBuilder;
import com.senla.courses.csv.ShopProductCsv;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

@Component
public class CsvParser {

    public List<ShopProductCsv> parseCsv(MultipartFile file) throws IOException {
        try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
            return new CsvToBeanBuilder<ShopProductCsv>(reader)
                    .withType(ShopProductCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build().parse();
        }
    }
}
