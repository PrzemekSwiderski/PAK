package fun.epak.pak.utility;

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
    private static final String UPLOAD_DIRECTORY = "testData";
    private static final String FILE_NAME = "test.txt";
    private static final Path UPLOAD_PATH = Paths.get(UPLOAD_DIRECTORY + "/" + FILE_NAME);

    @AfterEach
    public void deleteFiles() throws IOException {
            Files.deleteIfExists(UPLOAD_PATH);
    }

    @Test
    void shouldReadFille() throws IOException {
        //when
        FileUploadUtil
                .saveFile(UPLOAD_DIRECTORY,
                        FILE_NAME,
                        new MockMultipartFile("data",
                                "picture.txt",
                                "text/plain",
                                "test of File Reader".getBytes()));
        boolean result = Files.exists(UPLOAD_PATH);
        //then
        assertTrue(result);
    }

    @Test
    void shouldThrowException() {
        assertThatThrownBy(() -> FileUploadUtil
                .saveFile(UPLOAD_DIRECTORY,
                        FILE_NAME,
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