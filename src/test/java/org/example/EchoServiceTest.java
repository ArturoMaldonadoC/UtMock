package org.example;

import org.example.stubs.TestInputStream;
import org.example.stubs.TestOutputStream;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;

class EchoServiceTest {

    //TDD -> Tests driven development

    @Test
    public void givenValidRequestAndResponse_whenEcho_thenTrueIsResponded() throws IOException {
        //given:
        EchoService echoService = new EchoService();

        String request = "Hello World!";


        TestOutputStream outputStream = new TestOutputStream();
        InputStream inputStream = new TestInputStream(request);

        //when:
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        //then:
         String messageSent = outputStream.getMessageSent();

         assertEquals(request, messageSent);


         assertTrue(response);

    }

    @Test
    public void givenValidRequestAndWrongResponse_whenEcho_thenFalseIsResponded() throws IOException {
        //given:
        EchoService echoService = new EchoService();

        String request = "Hello World!";


        TestOutputStream outputStream = new TestOutputStream();
        InputStream inputStream = new TestInputStream("Otra cosa");

        //when:
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        //then:
        String messageSent = outputStream.getMessageSent();

        assertEquals(request, messageSent);


         assertTrue(response);

    }

}