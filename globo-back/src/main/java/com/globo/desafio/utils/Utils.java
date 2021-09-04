package com.globo.desafio.utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Utils {

	private static final String LISTA_PALAVROES_BLOQUEIO_TXT = "lista-palavroes-bloqueio.txt";

	public List<String> getListaBloqueio() {
		List<String> lines = new ArrayList<>();
		try {
			File file = getFileFromResource(LISTA_PALAVROES_BLOQUEIO_TXT);
			List<String> readAllLines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
			return readAllLines;

		} catch (IOException e) {
			log.error("An error occurred during call method getListaBloqueio", e);
		} catch (URISyntaxException e) {
			log.error("An error occurred during call method getListaBloqueio", e);
		}
		return lines;
	}

	/*
	 * The resource URL is not working in the JAR If we try to access a file that is
	 * inside a JAR, It throws NoSuchFileException (linux), InvalidPathException
	 * (Windows)
	 * 
	 * Resource URL Sample: file:java-io.jar!/json/file1.json
	 */
	private File getFileFromResource(String fileName) throws URISyntaxException {

		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(fileName);
		if (resource == null) {
			throw new IllegalArgumentException("file not found! " + fileName);
		} else {

			// failed if files have whitespaces or special characters
			// return new File(resource.getFile());

			return new File(resource.toURI());
		}

	}
}
