package comm;

import com.mashape.unirest.http.Headers;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class CommManager {
	private static final String baseURL = "http://hexmine.us-west-2.elasticbeanstalk.com";
	private static final String SESSION_KEY = "sessionID";
	
	
	private static String sessionID;
	volatile String idback;
	private static String status;
	
	public CommManager(String name, String map)
	{
		try {
			HttpResponse<String> j = Unirest.post(baseURL +"/login")
					  .field("nickName", name)
					  .field("puzzle", map)
					  .asString();
			
			//sessionID = Integer.valueOf(j.getBody().getObject().getInt(SESSION_KEY));
			
			idback = j.getBody();
							
			System.out.println(idback);
			
		} catch (UnirestException e) {
	
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		/*
		 * check status m8
		 */
		Thread x = new Thread(){
			public void run(){
				while(true){
					try {
						HttpResponse<String> s = Unirest.get(baseURL+"/check")
								  .header("cookie", idback)
								  .asString();
						
						status = s.getBody();
						System.out.println(status);
						try {
							Thread.sleep(3000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (UnirestException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
		    }
	    };
	    x.start();
				
		/*
		 * start the mutherfucking game
		 */
		
		try {			
			if(idback==null){
				idback="";
			}
			System.out.println("Starting");

			System.out.println(idback);

			HttpResponse<String> g = Unirest.post(baseURL+"/start").asString();
			System.out.println(g.getBody());

			try {
				Thread.sleep(6000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			g = Unirest.post(baseURL+"/solved").asString();
			System.out.println(g.getBody());
			
		} catch (UnirestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	

}
