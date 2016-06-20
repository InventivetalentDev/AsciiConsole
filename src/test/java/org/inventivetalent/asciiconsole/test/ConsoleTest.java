package org.inventivetalent.asciiconsole.test;

import org.inventivetalent.asciiconsole.AsciiConsole;
import org.junit.Test;

import java.awt.*;

public class ConsoleTest {

	@Test
	public void stringTest() {
		AsciiConsole console = new AsciiConsole(System.out, false).emptyImage(100, 20).drawStringCentered("Hello World!", 0, 0, 100, 20, new Font("Arial", Font.PLAIN, 18));
		console.print();
	}

	@Test
	public void invertedStringTest() {
		AsciiConsole console = new AsciiConsole(System.out, true).emptyImage(100, 20).drawStringCentered("Hello World!", 0, 0, 100, 20, new Font("Arial", Font.PLAIN, 18));
		console.print();
	}

}
