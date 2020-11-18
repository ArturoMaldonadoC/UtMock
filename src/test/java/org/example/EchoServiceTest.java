package org.example;

import org.example.stubs.TestInputStream;
import org.example.stubs.TestOutputStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EchoServiceTest {

    //TDD -> Tests driven development

    @Test
    public void givenValidRequestAndResponse_whenEcho_thenTrueIsResponded() throws IOException {
        //given:
        EchoService echoService = new EchoService();

        String request = "Hello World!";
        byte []messageInBytes = new byte[]{
                'H','e','l','l','o',' ','W','o','r','l','d','!'
        };


        OutputStream outputStream = mock(OutputStream.class);
        InputStream inputStream = mock(InputStream.class);

        when(inputStream.readAllBytes()).thenReturn(messageInBytes);

        //when:
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        //then:

        verify(inputStream).readAllBytes();
        verify(outputStream).write(messageInBytes);
        verifyNoMoreInteractions(inputStream, outputStream);


        assertTrue(response);

    }

    @Test
    public void givenValidRequestAndWrongResponse_whenEcho_thenFalseIsResponded() throws IOException {
        //given:
        EchoService echoService = new EchoService();

        String request = "Hello World!";
        byte []messageResponse = new byte[]{
                'H','e','l','l','o',' ','W','o','r','l','d','!','!'

        };
        byte []messageRequest = new byte[]{
                'H','e','l','l','o',' ','W','o','r','l','d','!'
        };


        OutputStream outputStream = mock(OutputStream.class);
        InputStream inputStream = mock(InputStream.class);

        when(inputStream.readAllBytes()).thenReturn(messageResponse);

        //when:
        boolean response = echoService.sendEchoMessage(request, outputStream, inputStream);

        //then:


         verify(inputStream).readAllBytes();
         verify(outputStream).write(messageRequest);
         verifyNoMoreInteractions(inputStream, outputStream);

        assertFalse(response);

    }

}