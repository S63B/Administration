package Project.Pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;

/**
 * Created by Nekkyou on 4-4-2017.
 */
public class PdfFonts {
	public static Font catFont = 		new Font(Font.FontFamily.TIMES_ROMAN, 18,	Font.BOLD);
	public static Font redFont = 		new Font(Font.FontFamily.TIMES_ROMAN, 12,	Font.NORMAL, BaseColor.RED);
	public static Font subFont = 		new Font(Font.FontFamily.TIMES_ROMAN, 16,	Font.BOLD);
	public static Font smallBold = 		new Font(Font.FontFamily.TIMES_ROMAN, 12,	Font.BOLD);
}
