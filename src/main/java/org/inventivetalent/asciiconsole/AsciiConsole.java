package org.inventivetalent.asciiconsole;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.PrintStream;

public class AsciiConsole {

	private final PrintStream output;
	private final boolean     inverted;

	private BufferedImage currentImage;
	private Graphics2D    currentGraphics;

	public AsciiConsole(PrintStream output, boolean inverted) {
		this.output = output;
		this.inverted = inverted;
	}

	public AsciiConsole loadImage(BufferedImage image) {
		this.currentImage = image;
		return this;
	}

	public AsciiConsole emptyImage(int width, int height) {
		this.currentImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		this.currentGraphics = this.currentImage.createGraphics();
		return this;
	}

	public AsciiConsole drawString(String string, int x, int y, Font font) {
		if (this.currentGraphics == null) {
			throw new IllegalStateException("image not initialized");
		}
		if (font != null) { this.currentGraphics.setFont(font); }
		this.currentGraphics.drawString(string, x, y);
		return this;
	}

	public AsciiConsole drawStringCentered(String text, int x, int y, int width, int height, Font font) {
		if (font != null) { this.currentGraphics.setFont(font); }
		FontMetrics metrics = this.currentGraphics.getFontMetrics(this.currentGraphics.getFont());
		x += (width - metrics.stringWidth(text)) / 2;
		y += ((height - metrics.getHeight()) / 2) + metrics.getAscent();
		this.currentGraphics.drawString(text, x, y);
		return this;
	}

	public void print() {
		if (this.currentImage == null) { throw new IllegalStateException("image not yet set"); }

		for (int y = 0; y < this.currentImage.getHeight(); y++) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int x = 0; x < this.currentImage.getWidth(); x++) {
				if (this.currentImage.getRGB(x, y) == -16777216) {
					stringBuilder.append(inverted ? "$" : " ");
				} else {
					stringBuilder.append(inverted ? " " : "$");
				}
			}

			if (!stringBuilder.toString().trim().isEmpty()) {// Skip empty lines
				this.output.println(stringBuilder.toString());
			}
		}
	}

}
