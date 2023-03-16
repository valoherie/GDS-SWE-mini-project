package users.service;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.transaction.Transactional;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import users.constants.Constants;
import users.entity.UsersEntity;
import users.repository.UsersRepository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class UploadService {

    @Autowired
    UsersRepository usersRepository;

    @Transactional
    public Integer uploadFile(MultipartFile file) throws IOException, InvalidFormatException, Exception {
        List<UsersEntity> users = new ArrayList<>();
        if (!file.getContentType().equalsIgnoreCase("text/csv")) {
            throw new IllegalArgumentException("File must be in csv format.");
        }
        File convertedFile = convertMultipartFiletoFile(file);
        CSVFormat format = CSVFormat.EXCEL.withHeader().withDelimiter(',');
        try (CSVParser lines = new CSVParser(new FileReader(convertedFile), format)) {
            List<String> header  = lines.getHeaderNames();
            //Acceptance criteria 3
            if (header.size() != 2) {
                    throw new IllegalArgumentException(Constants.INVALID_FILE_COLUMNS);
            }
            for (CSVRecord line : lines) {
                if (line.size() > 2) {
                    throw new IllegalArgumentException(Constants.INVALID_FILE_COLUMNS);
                }
                UsersEntity usersEntity = new UsersEntity();
                UsersEntity usersEntity1 = usersRepository.findByName(line.get("Name"));
                if (usersEntity1 != null) {
                    if (Objects.nonNull(usersEntity1.getName())) {
                        if (Float.parseFloat(line.get("Salary")) >= 0) {
                            usersEntity1.setSalary((Float.valueOf(line.get("Salary"))));
                            usersRepository.save(usersEntity1);
                        } else {
                            throw new IllegalArgumentException(Constants.INVALID_SALARY);
                        }
                    }
                } else {
                    if (!users.contains(line.get("Name")) && Float.parseFloat(line.get("Salary")) >= 0) {
                        usersEntity.setName(line.get("Name"));
                        usersEntity.setSalary((Float.valueOf(line.get("Salary"))));
                        users.add(usersEntity);
                    } else if (Float.parseFloat(line.get("Salary")) < 0) {
                        throw new IllegalArgumentException(Constants.INVALID_SALARY);
                    }
                }
            }
            usersRepository.saveAll(users);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return 0;
        }
        return 1;
    }

    private File convertMultipartFiletoFile(MultipartFile multipartFile) throws FileNotFoundException, IOException {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            if (file.exists()) {
                fos.write(multipartFile.getBytes());
            }
        } catch (Exception e) {
            System.out.println(Constants.ERROR_MESSAGE + e.getMessage());
        }
        return file;
    }
}
