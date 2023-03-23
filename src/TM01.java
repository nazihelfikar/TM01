import Tm01.Connect;
import org.json.JSONArray;
import org.json.JSONObject;
import responModel.ResponModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class TM01 extends JFrame {
    private JButton submiteButton;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton closeButton;
    private JPanel Display;

    private JFrame Fikar;


    public TM01(){
        Fikar = new JFrame("TM01_22090082_Nazihelfikar");
        Fikar.setDefaultCloseOperation(EXIT_ON_CLOSE);
        Fikar.setPreferredSize(new Dimension(500, 1000));
        Fikar.setResizable(true);
        Fikar.add(Display);
        Fikar.pack();
        Fikar.setLocationRelativeTo(null);

        submiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource()== submiteButton) {
                    try {
                        URL url = new URL("https://harber.mimoapps.xyz/api/getaccess.php");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("GET");

                        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String inputLine;
                        StringBuffer response = new StringBuffer();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();

                        JSONArray jsonArray = new JSONArray(response.toString());

                        ArrayList<ResponModel> parsedList = new ArrayList<>();
                        for (int index = 0; index < parsedList.size(); index++) {
                            ResponModel responModel = new ResponModel();
                            JSONObject myJSONobject = jsonArray.getJSONObject(index);
                            responModel.setMessage(myJSONobject.getString("message"));
                            responModel.setStatus(myJSONobject.getString("status"));
                            responModel.setComment(myJSONobject.getString("comment"));
                            parsedList.add(responModel);

                        }
                        for (int index = 0; index < parsedList.size(); index++) {
                            textField1.setText(parsedList.get(index).getMessage());
                            textField2.setText(parsedList.get(index).getStatus());
                            textField3.setText(parsedList.get(index).getComment());


                        }
                    } catch (ProtocolException ex) {
                        throw new RuntimeException(ex);
                    } catch (MalformedURLException ex) {
                        throw new RuntimeException(ex);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connect koneksisaya = new Connect();
                URL myAddress = koneksisaya.buildURL("https://harber.mimoapps.xyz/api/getaccess.php");
                String response = null;
                try {
                    response = koneksisaya.getResponseFromHttpUrl(myAddress);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("");
                System.out.println(response);
                System.out.println("");
                System.out.println("");
                System.out.println("*  terimakasih  *");
                System.exit(0);
            }
        });
        Fikar.setVisible(true);

    }

    public static void main(String[] args) {
        new TM01();
    }
}
