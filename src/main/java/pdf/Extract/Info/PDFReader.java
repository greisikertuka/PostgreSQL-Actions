package pdf.extract.info;

import jdbc.actions.Connectivity;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PDFReader {
    public static void main(String[] args) throws IOException, SQLException {
        Connectivity connectivity = new Connectivity();
        Connection connection = connectivity.getConnection();
        File f = new File("dokumenta");
        String[] pdfDocuments = f.list();
        if (f.list() == null) {
            System.out.println("Nuk ka file te direktoria dokumenta!");
            return;
        }
        for (String pdfDocument : pdfDocuments) {
            PDDocument document = PDDocument.load(new File("dokumenta/" + pdfDocument));
            if (!document.isEncrypted()) {
                PDFTextStripper stripper = new PDFTextStripper();
                String text = stripper.getText(document);
                String[] nr = text.split("\n");
                for (String person : nr) {
                    String[] splited = person.split("\\s+");

                    if (splited[1].equals("NAME"))
                        break;

                    String sql = "INSERT INTO numra_telefoni (emri, mbiemri, kodi, numri)" +
                            " VALUES (?, ?, ?, ?)";

                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, splited[1]);
                    preparedStatement.setString(2, splited[2]);
                    preparedStatement.setString(3, splited[3]);
                    preparedStatement.setString(4, splited[4]);

                    if (preparedStatement.executeUpdate() <= 0) {
                        System.out.println("Rekordi nuk u ruajt!");
                    }
                }
            }
            document.close();
        }
        connection.close();
    }
}