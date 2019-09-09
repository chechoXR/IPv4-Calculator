import java.util.Arrays;
import java.util.StringTokenizer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IPv4  extends Application{

	public static void main(String[] args) {

		Application.launch(args);


	}

	@Override
	public void start(Stage St) throws Exception {

		Pane P = new Pane();
		Scene Sc = new Scene(P);
		St.setScene(Sc);

		P.setMinSize(400, 400);

		Separator sp = new Separator(Orientation.HORIZONTAL);
		sp.setMinSize(380, 380);
		sp.setTranslateX(10);
		sp.setTranslateY(-30);

		Label Title = new Label("CALCULADORA DE IP");
		Title.setFont(Font.font("Ravie", 30));

		Label checho = new Label("Checho!");
		checho.setFont(Font.font("Ravie", 10));
		checho.setTranslateX(345);
		checho.setTranslateY(380);

		Label ip = new Label("IP:");
		ip.setTranslateX(55);
		ip.setTranslateY(60);

		TextField iptf = new TextField();
		iptf.setTranslateX(70);
		iptf.setTranslateY(56);
		iptf.setMaxSize(105, 20);

		TextField mr = new TextField();
		mr.setTranslateX(177);
		mr.setTranslateY(56);
		mr.setMaxSize(40, 20);
		mr.setDisable(true);



		Label mrl = new Label("Mascara:");
		mrl.setTranslateX(20);
		mrl.setTranslateY(90);

		TextField masc = new TextField();
		masc.setTranslateX(70);
		masc.setTranslateY(90);
		masc.setMaxSize(105, 20);
		masc.setDisable(true);



		Label Lhost = new Label("Cantidad de equipos:");
		Lhost.setTranslateX(220);
		Lhost.setTranslateY(90);

		TextField tfHost = new TextField();
		tfHost.setTranslateX(335);
		tfHost.setTranslateY(90);
		tfHost.setMaxSize(40, 20);

		Button calcular = new Button("Calcular!"); 
		calcular.setTranslateX(325);
		calcular.setTranslateY(125);

		
//________________________________________________________________________________________
//		System.out.print(System.getProperty("user.dir"));
//		System.out.print("\\Img\\logopoli.png");


		Label dRed = new Label("Dirección de red:");
		dRed.setTranslateX(53);
		dRed.setTranslateY(170);
		
		TextField Red = new TextField();
		Red.setTranslateX(145);
		Red.setTranslateY(168);
		Red.setDisable(true);
		
		Label dBroadcast = new Label("Dirección Broadcast:");
		dBroadcast.setTranslateX(35);
		dBroadcast.setTranslateY(200);
		
		TextField Broadcast = new TextField();
		Broadcast.setTranslateX(145);
		Broadcast.setTranslateY(198);
		Broadcast.setDisable(true);
		
		Label ips = new Label("IP totales:");
		ips.setTranslateX(88);
		ips.setTranslateY(225);
		
		TextField cantIP = new TextField();
		cantIP.setTranslateX(145);
		cantIP.setTranslateY(225);
		cantIP.setMaxSize(50, 20);
		cantIP.setDisable(true);

		
		//String dir=System.getProperty("user.dir");
		Image logo;

		
//		logo = new Image(new FileInputStream(new File("file:logopoli.png")));		
		logo= new Image("logopoli.png");
//		try {
//			logo = new Image(new FileInputStream(new File(dir + "\\Image\\logopoli.png")));		
//		} catch (FileNotFoundException e) {
////			logo= null;
//			logo = new Image(new FileInputStream(new File("logopoli.png")));		
//			
//		}

		
		ImageView logopoli = new ImageView(logo);
		logopoli.setFitHeight(160);
		logopoli.setFitWidth(240);
		
		logopoli.setTranslateX(75);
		logopoli.setTranslateY(250);
		
		P.setBackground(Background.EMPTY);
		P.getChildren().addAll(sp,Title,checho,ip,iptf,mr,mrl,masc,Lhost, tfHost,
				calcular, dRed, Red, dBroadcast, Broadcast, ips, cantIP, logopoli);
		St.setResizable(false);
		

		St.setTitle("Calculadora de IPv4 By:Checho        v1.0");
		
		St.show();
		
		
		

		calcular.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {

				if ((iptf.getText().equals(""))||(tfHost.getText().equals(""))) {
					Alert ci = new Alert(AlertType.WARNING);
					ci.setTitle("Campos incompletos");
					ci.setContentText("El campo de \"IP\" o de \"Cantidad de equipos\" esta vacio!");
					ci.show();					
				}
				else {
				
				//Mascara
				double u=32-Math.log(Double.parseDouble(tfHost.getText())+2)/Math.log(2);
				String ips = String.valueOf(Math.floor(32-Math.log(Double.parseDouble(tfHost.getText())+2)/Math.log(2)));
				mr.setText(ips.substring(0, ips.length()-2));
		
				
				//____________________________________

				
				
				
				String msc [] = new String[4];
				Arrays.fill(msc, "");
				
				for (int i = 0; i < msc.length;i++ ) 
					for (int j = 0; j < 8; j++) 
						msc[i]+= (--u>0) ?1:0;
						
						
//				System.out.println(Arrays.toString(msc));
				String m="";
				for (int i = 0; i < msc.length;i++ )
					 m+=(Integer.parseInt(msc[i],2)+".");
				masc.setText(m.substring(0, m.length()-1));
				
				//____________________________________
				
				String totalIP=String.valueOf(Math.pow(2, 32-(Integer.parseInt(mr.getText()))));
				cantIP.setText(totalIP.substring(0, totalIP.length()-2));
				//____________________________________
				
				StringTokenizer st = new StringTokenizer(iptf.getText(), "."); 
				
				String [] ipbin= new String [4];
				
				int i=0;
				while(st.hasMoreTokens())
					ipbin[i++]=String.valueOf(Integer.toString(Integer.parseInt(st.nextToken()),2));
				

				String ip="";
				for (int j = 0; j < ipbin.length; j++) {
					String s="";
					if(ipbin[j].length()<8)
					{	for (int k = 0; k < 8-ipbin[j].length(); k++) 
							s+=0;
					ipbin[j]=s+ipbin[j];
					}
				}
				
//				System.out.println(Arrays.toString(ipbin));
				
				for (int j = 0; j < ipbin.length; j++) 
					ip+=ipbin[j];
					
				
//				System.out.println(ip);
				
				String min="", max="";
				for (int j = 0; j < 32-Integer.parseInt(mr.getText()); j++) {
					min+=0;
					max+=1;
				}
				
				String minIP=ip.substring(0, Integer.parseInt(mr.getText()))+min;
				String maxIP=ip.substring(0, Integer.parseInt(mr.getText()))+max;
				
//				System.out.println(minIP);
//				System.out.println(maxIP);
				
				String [] dirRed = new String [4];
				String [] dirBr = new String [4];
				
				int aux=0;
				for (int j = 1; j < dirBr.length+1; j++) {
					
					dirRed[j-1]=minIP.substring(aux, j*8);
					dirBr[j-1]=maxIP.substring(aux, j*8);
					aux+=8;
				}
				
//				System.out.println(Arrays.toString(dirRed));
//				System.out.println(Arrays.toString(dirBr));
				
				String r="", ma="";
				for (int j = 0; j < dirBr.length; j++) {
				
					r+=String.valueOf(Integer.parseInt(dirRed[j],2))+".";
					ma+=String.valueOf(Integer.parseInt(dirBr[j],2))+".";
				}
				Red.setText(r.substring(0,r.length()-1));
				Broadcast.setText(ma.substring(0,ma.length()-1));
				
			}
			}		
		});
		
	}
	
}
