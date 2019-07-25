package ua.com.ticketservise.servises;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ua.com.ticketservise.model.Ticket;
import ua.com.ticketservise.repo.TicketRepo;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@Component
public class ScheduledTasks {
    private TicketRepo ticketRepo;



    public ScheduledTasks(TicketRepo ticketRepo) {
        this.ticketRepo = ticketRepo;
    }

    @Scheduled(cron = "1 * * * * *") // 1 minute cron


    public void performTaskUsingCron() throws Exception {
        System.out.println("Regular task performed using Cron at  1 minute");
        try {
            List <Ticket> tk = ticketRepo.findAllByPayStatusIsNull();
            System.out.println(tk.size());
            for(int i = 0;i < tk.size(); i++){
                String id ="id=" + String.valueOf(tk.get(i).getId());

                URL obj = new URL("http://localhost:8080/pay");
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("POST");

                con.setDoOutput(true);
                OutputStream os = con.getOutputStream();
                os.write(id.getBytes());
                os.flush();
                os.close();

                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(
                            con.getInputStream()));
                    String inputLine;
                    StringBuffer response = new StringBuffer();

                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Ticket ticket = tk.get(i);
                    if (response.toString().equals("true")){
                        ticket.setPayStatus(Boolean.TRUE);
                        ticketRepo.save(ticket);
                    }if(response.toString().equals("false")){
                        ticket.setPayStatus(Boolean.FALSE);
                        ticketRepo.save(ticket);
                    }
                } else {
                    System.out.println("Error: POST request not worked");
                }
            }

        } catch (NullPointerException e) {
            System.out.println("null error");
        }


    }

}








