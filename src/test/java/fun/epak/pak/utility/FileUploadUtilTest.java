package fun.epak.pak.utility;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileUploadUtilTest {
    String uploadDirectory = "testData";
    String fileName = "test.txt";
    Path uploadPath = Paths.get(uploadDirectory + "/" + fileName);


    @AfterEach
    public void deleteFiles(){
        try {
            Files.deleteIfExists(uploadPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldReadFille() {
        //when
        try {
            FileUploadUtil
                    .saveFile(uploadDirectory,
                            fileName,
                            new MockMultipartFile("data",
                                    "picture.txt",
                                    "text/plain",
                                    "test of File Reader".getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        boolean result = Files.exists(uploadPath);
        //then
        assertTrue(result);
    }

    @Test
    void shouldThrowException() {
        assertThatThrownBy(() -> FileUploadUtil
                .saveFile(uploadDirectory,
                        fileName,
                        new CustomMockMultipartFile("data",
                                "picture.txt",
                                "text/plain",
                                "picture".getBytes())))
                .isInstanceOf(IOException.class);
    }

    private static class CustomMockMultipartFile extends MockMultipartFile {

        private CustomMockMultipartFile(String name, String originalFilename, String contentType, byte[] content) {
            super(name, originalFilename, contentType, content);
        }

        @Override
        public InputStream getInputStream() throws IOException {
            throw new IOException();
        }
    }
}