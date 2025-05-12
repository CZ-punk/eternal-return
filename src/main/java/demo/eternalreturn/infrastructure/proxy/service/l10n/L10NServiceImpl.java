package demo.eternalreturn.infrastructure.proxy.service.l10n;

import com.fasterxml.jackson.databind.JsonNode;
import demo.eternalreturn.infrastructure.proxy.constant.UrlConst;
import demo.eternalreturn.infrastructure.proxy.service.util.JsonNodeService;
import demo.eternalreturn.presentation.exception.CustomException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static demo.eternalreturn.infrastructure.proxy.constant.DataNodeConst.DATA;
import static demo.eternalreturn.infrastructure.proxy.constant.DataNodeConst.L10N_PATH;

@Slf4j
@Service
@RequiredArgsConstructor
public class L10NServiceImpl implements L10NService {

    @Autowired
    private final JsonNodeService jsonNodeService;

    @Override
    @Transactional
    public Mono<?> callL10n(String language) {
        JsonNode jsonNode = jsonNodeService.getMonoJsonNodeByPathVariable(UrlConst.L10N, language, HttpMethod.GET).block();
        JsonNode dataNode = jsonNodeService.checkNodeByName(jsonNode, DATA);
        JsonNode urlNode = jsonNodeService.checkNodeByName(dataNode, L10N_PATH);
        String urlString = urlNode.asText();

        saveUrlContentToFile(urlString);
        return Mono.just("File saved successfully.");
    }

    private void saveUrlContentToFile(String urlString) {
        try {
            String projectDir = System.getProperty("user.dir");
            Path fullPath = Paths.get(projectDir, "src", "main", "resources", "files", "l10n-content.txt");

            Path path = fullPath.getParent();
            if (path != null && !Files.exists(path)) {
                Files.createDirectories(path);
            }

            log.info("urlString: {}", urlString);
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                     FileWriter writer = new FileWriter(fullPath.toFile())) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        writer.write(inputLine);
                        writer.write(System.lineSeparator());
                    }
                }
            } else {
                throw new CustomException(HttpStatus.NOT_ACCEPTABLE, "l10n 서버에서 막음");
            }
        } catch (Exception e) {
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
        }
    }
}
