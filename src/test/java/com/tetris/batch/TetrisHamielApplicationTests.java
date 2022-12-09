package com.tetris.batch;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = {"input.fileName=src/main/resources/providedInput.txt", "output.fileName=src/test/resources/context-dummy.txt"})
class TetrisHamielApplicationTests {

	@Test
	void contextLoads() {
	}

}
