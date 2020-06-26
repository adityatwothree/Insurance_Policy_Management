import java.sql.*;
import javafx.application.*;
import javafx.scene.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.event.*;

import javafx.geometry.*;
public class Proj extends Application 
{
	int f=0;
	TextField tf;
	TextField tf1;
	TextField tf2;
	TextField tf3;
	TextField tf4;
	Button CloseBut=new Button("Back");

	
	PasswordField pf;
	Label l1;
	Label l2;
	Label response;
	Label response1;
	Label response2;
	public static void main(String[] args)
	{
		launch(args);
	}
	public void start(Stage stage1)
	{
		stage1.setTitle("Insurance");
		FlowPane root=new FlowPane(10,10);
		root.setAlignment(Pos.CENTER);
		Scene scene1=new Scene(root,240,400);
		stage1.setScene(scene1);
		tf=new TextField();
		tf.setPromptText("Email id");
		l1=new Label("Email id");
		l2=new Label("Password");
		response=new Label();
		pf=new PasswordField();
		Button log=new Button("Login");
		log.getStylesheets().add(this.getClass().getResource("start.css").toExternalForm());
		log.setMinSize(40, 40); log.setMaxSize(40,40);
		Button CloseB=new Button("Back");
		CloseB.getStylesheets().add(this.getClass().getResource("button.css").toExternalForm());
		CloseB.setMinSize(50, 40); CloseB.setMaxSize(50,40);
		BackgroundImage myBI= new BackgroundImage(new Image("1.jpeg",240,400,false,true),
		        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		root.setBackground(new Background(myBI));
		
		// test over for bg image
		log.setOnAction(new EventHandler<ActionEvent>()
		{
			public void handle(ActionEvent ae)
			{
				String eid=tf.getText();
				String pw=pf.getText();
				try
				{
					Connection c=DriverManager.getConnection("jdbc:sqlite:file:Login.db");
					Statement st=c.createStatement();
					ResultSet res=st.executeQuery("select * from Log;");
					while(res.next())
					{
						if(res.getString("Email_Id").contentEquals(eid)&&res.getString("Password").contentEquals(pw))
						{
							f=1;
							stage1.close();
							
							VBox root=new VBox(10);
							root.setAlignment(Pos.CENTER);
							Scene scene2=new Scene(root,260,400);
							stage1.setScene(scene2);
							BackgroundImage myBI= new BackgroundImage(new Image("2.jpeg",240,400,false,true),
							        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
							          BackgroundSize.DEFAULT);
							//then you set to your node
							root.setBackground(new Background(myBI));
							
							//show all customers on console that are availaible
							Button shwall=new Button("Show All Policy");
							shwall.setOnAction(new EventHandler<ActionEvent>()
								{
									public void handle(ActionEvent ae) {
									//	stage1.close();
										try
										{
											Connection conn=DriverManager.getConnection("jdbc:sqlite:policy.sqlite");
											Statement st=conn.createStatement();
											ResultSet res=st.executeQuery("select * from Policy;");
											System.out.println("Policy NO\t\tPolicy Name\t\tPolicy Type");
											while(res.next())
											{
												System.out.println("\t"+res.getInt("PolicyNO")+"\t\t"+res.getString("PolicyName")+"\t\t"+res.getString("PolicyType"));
											}
										}	
										catch(SQLException e) {
											response.setText("Error IN Reading");
										}
									}
								
								});
							
							Button Cshwall=new Button("Show All Customer");
							Cshwall.setOnAction(new EventHandler<ActionEvent>()
								{
									public void handle(ActionEvent ae) {
								//		stage1.close();
										try
										{
											Connection conn=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
											Statement s=conn.createStatement();
											ResultSet res=s.executeQuery("Select * from Customer ;");
											System.out.println("Customer Id \t\t Name \t\t PolicyNo");
											while(res.next()) {
												System.out.println("\t"+res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t"+res.getInt("PolicyNo"));
												
										}}
										catch(SQLException e)
										{
											System.out.println("Caught an error");
										}			
									}
								
								});
							
							
							
							
							
							Button ins=new Button("Insert a new Customer");
							ins.setOnAction(new EventHandler<ActionEvent>()
							{
								public void handle(ActionEvent ae)
								{	stage1.close();
									Stage stage5=new Stage();
									FlowPane root=new FlowPane(10,10);
								root.setAlignment(Pos.CENTER);
								
									Scene insScene=new Scene(root,240,400);
									stage5.setScene(insScene);
									
									stage5.setTitle("Insert Customer");
									
									TextField tf1=new TextField();
									TextField tf2=new TextField();
									TextField tf3=new TextField();
									TextField tf4=new TextField();
									TextField tf5=new TextField();
									TextField tf6=new TextField();
									BackgroundImage myBI= new BackgroundImage(new Image("3.jpeg",240,400,false,true),
									        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
									          BackgroundSize.DEFAULT);
									root.setBackground(new Background(myBI));
									
									
									CloseB.setOnAction(new EventHandler<ActionEvent>()
							     	{
										public void handle(ActionEvent ae)
										{	response.setText("  ");
											stage1.show();
											stage5.close();
										}
							     	});
											
									
									Label response1=new Label("Customer Id ");
									
									Label response2=new Label("Cust. Name");
									Label response3=new Label("Policy No. ");
									Label response4=new Label("Term Of Policy");
									Label response5=new Label("Total Amount");
									Label response6=new Label("Monthly EMI");
									response1.setTextFill(Color.RED);
									response2.setTextFill(Color.RED);
									response3.setTextFill(Color.RED);
									response4.setTextFill(Color.RED);
									response5.setTextFill(Color.RED);
									response6.setTextFill(Color.RED);
									Button ins1=new Button("Insert");
						
									ins1.setOnAction(new EventHandler<ActionEvent>()
									{
										public void handle(ActionEvent ae)
										{
											String c_id=tf1.getText();
											String c_name=tf2.getText();
											String p_no=tf3.getText();
											String p_t=tf4.getText();
											String p_a=tf5.getText();
											String p_emi=tf6.getText();
											int tc_id=Integer.parseInt(c_id);
											int tp_no=Integer.parseInt(p_no);
											int tp_t=Integer.parseInt(p_t);
											int tp_a=Integer.parseInt(p_a);
											int tp_emi=Integer.parseInt(p_emi);
											
											try
											{
												Connection conn=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
												Statement st=conn.createStatement();
												st.execute("insert into Customer VALUES("+tc_id+","+"'"+c_name+"'"+","+tp_no+","+tp_t+","+tp_a+","+tp_emi+")");
												 try { st.close(); }
												       catch (Exception e) { /* ignored */ }
												 try { conn.close(); } 
												       catch (Exception e) { /* ignored */ }
											}
											catch(SQLException e)
											{
												response.setText("Insertion Error");
											}
											response.setTextFill(Color.PINK);
											response.setText("Inserted Succesfully");
										}
									});	
									root.getChildren().addAll(response1,tf1,response2,tf2,response3,tf3,response4,tf4,response5,tf5,response6,tf6,CloseB,ins1,response);
									stage5.show();
								}
							});
							Button del=new Button("Delete an existing Customer");
							del.setOnAction(new EventHandler<ActionEvent>()
							{
								public void handle(ActionEvent ae)
								{	stage1.close();
								    Stage stage5=new Stage();
									FlowPane root=new FlowPane(10,10);
									root.setAlignment(Pos.CENTER);
									Scene insScene=new Scene(root,260,400);
									stage5.setScene(insScene);
									stage5.setTitle("Delete Customer");
									BackgroundImage myBI= new BackgroundImage(new Image("4.jpeg",240,400,false,true),
									        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
									          BackgroundSize.DEFAULT);
									root.setBackground(new Background(myBI));
									tf1=new TextField();
								//	tf2=new TextField();
									response1=new Label("Customer Id");
								//	response2=new Label("Policy Type");	
									
									CloseB.setOnAction(new EventHandler<ActionEvent>()
							     	{
										public void handle(ActionEvent ae)
										{	response.setText("Delete a Record");
											stage5.close();
											stage1.show();
										}
							     	});
									
									Button Del_but=new Button("Delete");
									Del_but.setOnAction(new EventHandler<ActionEvent>()
									{
										public void handle(ActionEvent ae)
										{	response.setText("Delete A record ");
											String c_id=tf1.getText();
											int tc_id=Integer.parseInt(c_id);
										
											try
											{
												Connection conn=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
												Statement st=conn.createStatement();
												st.execute("delete from Customer where CusId="+tc_id);
												 try { st.close(); } catch (Exception e) { /* ignored */ }
												    try { conn.close(); } catch (Exception e) { /* ignored */ }
									        }
											catch(SQLException e)
											{
												response.setText("caught");
											}
											response.setText("Deleted Succesfully");
										}
									});	
									root.getChildren().addAll(response1,tf1,Del_but,CloseB,response);
									stage5.show();
								}
							});
							Button sel=new Button("Select an Existing Customer");
							sel.setOnAction(new EventHandler<ActionEvent>()
							{
								public void handle(ActionEvent ae)
								{	
									stage1.close();
									Stage stage5=new Stage();
									VBox root=new VBox(20);
									root.setAlignment(Pos.CENTER);
									Scene selScene=new Scene(root,260,400);
									stage5.setScene(selScene);
									stage5.setTitle("Select Customer");
									BackgroundImage myBI= new BackgroundImage(new Image("4.jpeg",240,400,false,true),
									        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
									          BackgroundSize.DEFAULT);
									root.setBackground(new Background(myBI));
									Button btn1=new Button("Term of policy");
									Button btn2=new Button("Total Cost of policy");
									Button btn3=new Button("Amount per month");
									Button btn4 =new Button("Customer Id");
									
									CloseB.setOnAction(new EventHandler<ActionEvent>()
							     	{
										public void handle(ActionEvent ae)
										{
											stage5.close();
											stage1.show();
										}
							     	});
									
									btn1.setOnAction(new EventHandler<ActionEvent>()
									{
										public void handle(ActionEvent be)
										{	
								//			stage1.close();
											stage5.close();
											Stage stage2=new Stage();
											
											stage2.setTitle("Term Selection");
											
											FlowPane term_root=new FlowPane(10,10);
											Scene term=new Scene(term_root,260,400);
											BackgroundImage myBI= new BackgroundImage(new Image("1.jpeg",240,400,false,true),
											        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
											          BackgroundSize.DEFAULT);
											term_root.setBackground(new Background(myBI));
											stage2.setScene(term);
											tf3=new TextField();
											tf3.setPromptText("Term");
											
											Label response =new Label();
											response2=new Label("Term");
											Button Submit_temp =new Button("Submit");
											
											CloseB.setOnAction(new EventHandler<ActionEvent>()
									     	{
												public void handle(ActionEvent ae)
												{
													stage2.close();
													stage5.show();
												}
									     	});
											
											Submit_temp.setOnAction(new EventHandler<ActionEvent>()
											{
												public void handle(ActionEvent ce)
												{
													String p_t=tf3.getText();
													int tp_t=Integer.parseInt(p_t);
													
													try
													{
														Connection c=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
														Statement s=c.createStatement();
														ResultSet res=s.executeQuery("Select * from Customer where TermOfPolicy="+tp_t+";");
														System.out.println("Customer Id \t\t Name \t\t Term");
														//response1.setText("Customer Id "+"\t\t"+" Name "+"\t\t "+"Term");
														while(res.next()) {
															System.out.println("\t"+res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t"+res.getInt("TermOfPolicy"));
//															response.setText(res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t\t"+res.getInt("TermOfPolicy"));
													}
														try { res.close(); } catch (Exception e) { /* ignored */ }
														 try { s.close(); } catch (Exception e) { /* ignored */ }
														    try { c.close(); } catch (Exception e) { /* ignored */ }
														}
													catch(SQLException e)
													{
														response.setText("caught");
													}	
												}
											});
											term_root.getChildren().addAll(response2,tf3,Submit_temp,response,CloseB);
										
											term_root.setAlignment(Pos.CENTER); 
											stage2.show();
										}
									});
									
									// button1 for existing policies choice ends here 
									
									btn2.setOnAction(new EventHandler<ActionEvent>()
									{
										public void handle(ActionEvent ae)
										{
											stage5.close();
											Stage stage3=new Stage();
											FlowPane cost_root=new FlowPane(10,10);
											Button Submit_temp =new Button("Submit");
											cost_root.setAlignment(Pos.CENTER);
											Scene total=new Scene(cost_root,260,400);
											stage3.setScene(total);
											stage3.setTitle("Total Cost Selection");
											
											BackgroundImage myBI= new BackgroundImage(new Image("1.jpeg",240,400,false,true),
											        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
											          BackgroundSize.DEFAULT);
											cost_root.setBackground(new Background(myBI));
											
											tf3=new TextField();
											tf3.setPromptText("Amount");
											response2=new Label("Total Amount");
											Label response =new Label();
											
											CloseB.setOnAction(new EventHandler<ActionEvent>()
									     	{
												public void handle(ActionEvent ae)
												{
													stage3.close();
													stage5.show();
												}
									     	});
											
											
											Submit_temp.setOnAction(new EventHandler<ActionEvent>()
											{
												public void handle(ActionEvent ce)
												{
													String v=tf3.getText();
													try
													{
														Connection c=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
														Statement s=c.createStatement();
														ResultSet res=s.executeQuery("Select * from Customer where TotalAmount='"+v+"';");
														System.out.println("Customer Id \t\t Name \t\t TotalAmount");
														//response1.setText("CustomerId\t\tName\t\tTotalAmount");
														while(res.next()) {
															System.out.println("\t"+res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t"+res.getInt("TotalAmount"));
															//response.setText(res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t"+res.getString("TotalAmount"));
													}}
													catch(SQLException e)
													{
														response.setText("Wrong Query");
													}			
												}
											});//inside wala term ends
											
											
											
											
											
											
											cost_root.getChildren().addAll(response2,tf3,Submit_temp,response,CloseB);
									//		root.getChildren().addAll(response2,tf3,Submit_temp);
											stage3.show();
										}
									});
									btn3.setOnAction(new EventHandler<ActionEvent>()
									{
										public void handle(ActionEvent ae)
										{
											stage5.close();
											Stage stage4=new Stage();
											Label response=new Label(); 
											FlowPane amount_root=new FlowPane(10,10);
											Scene month=new Scene(amount_root,260,400);
											stage4.setScene(month);
											stage4.setTitle("Monthly Pay Selection");
											Button Submit_temp =new Button("Submit");
											Submit_temp.setAlignment(Pos.BOTTOM_CENTER);
											
											BackgroundImage myBI= new BackgroundImage(new Image("1.jpeg",240,400,false,true),
											        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
											          BackgroundSize.DEFAULT);
											amount_root.setBackground(new Background(myBI));
											
											tf3=new TextField();
											tf3.setPromptText("Monthly");
											response2=new Label("Monthly EMI");
											
											CloseB.setOnAction(new EventHandler<ActionEvent>()
									     	{
												public void handle(ActionEvent ae)
												{
													stage4.close();
													stage5.show();
												}
									     	});
											
											
											Submit_temp.setOnAction(new EventHandler<ActionEvent>()
											{
												public void handle(ActionEvent ce)
												{
													String v=tf3.getText();
													int tp_emi=Integer.parseInt(v);
													try
														{
															Connection c=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
															Statement s=c.createStatement();
															ResultSet res=s.executeQuery("Select * from Customer where MonthlyEMI='"+tp_emi+"';");
														//	response1.setText("Customer Id\t\tName\t\tMonthlyEMI");
															System.out.println("Customer Id \t\t Name \t\t MonthlyEMI");
															while(res.next()) {
																System.out.println("\t"+res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t"+res.getInt("MonthlyEMI"));
																//response.setText(res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t"+res.getInt("MonthlyEMI"));
														}}
											catch(SQLException e)
											{
												response.setText("Wrong Emi");
											}	
												}
											});//inside wala term ends
											
										amount_root.getChildren().addAll(response2,tf3,Submit_temp,response,CloseB);
											amount_root.setAlignment(Pos.CENTER);
											Submit_temp.setAlignment(Pos.BOTTOM_CENTER);
											stage4.show();
										}
									});
						// adding new Button
									btn4.setOnAction(new EventHandler<ActionEvent>()
									{
										public void handle(ActionEvent ae)
										{
											stage5.close();
											Stage stage6=new Stage();
											Label response1=new Label(); 
											Label response=new Label(); 
											FlowPane cid_root=new FlowPane(10,10);
											Scene cid=new Scene(cid_root,260,400);
											stage6.setScene(cid);
											stage6.setTitle("Customer Id");
											Button Submit_temp =new Button("Submit");
											Submit_temp.setAlignment(Pos.BOTTOM_CENTER);
											
											BackgroundImage myBI= new BackgroundImage(new Image("1.jpeg",240,400,false,true),
											        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
											          BackgroundSize.DEFAULT);
											cid_root.setBackground(new Background(myBI));
											
											tf3=new TextField();
											tf3.setPromptText("C_id");
											response2=new Label("Customer Id");
											
											CloseB.setOnAction(new EventHandler<ActionEvent>()
									     	{
												public void handle(ActionEvent ae)
												{
													stage6.close();
													stage5.show();
												}
									     	});
											
											response.setTextFill(Color.RED);
											Submit_temp.setOnAction(new EventHandler<ActionEvent>()
											{
												public void handle(ActionEvent ce)
												{
													String v=tf3.getText();
													int tc_id=Integer.parseInt(v);
													try
														{
															Connection c=DriverManager.getConnection("jdbc:sqlite:adi.sqlite");
															Statement s=c.createStatement();
															ResultSet res=s.executeQuery("Select * from Customer where CusId='"+tc_id+"';");
															response1.setText("Customer Id\t\tName\t\tPolicyNO");
														
															response.setText("\t"+res.getInt("CusId")+"\t\t"+res.getString("CustomerName")+"\t\t\t\t"+res.getInt("PolicyNO"));
														}
											catch(SQLException e)
											{
												response.setText("Wrong C_id");
											}	
												}
											});//inside wala term ends
											
										cid_root.getChildren().addAll(response2,tf3,Submit_temp,response1,response,CloseB);
							//				amount_root.getChildren().addAll(response2,tf3,Submit_temp);
											cid_root.setAlignment(Pos.CENTER);
											Submit_temp.setAlignment(Pos.BOTTOM_CENTER);
											stage6.show();
										}
									});
									
									
						// end of new Button			
									root.getChildren().addAll(btn1,btn2,btn3,btn4,CloseB);
									stage5.show();						
								}
							});
							root.getChildren().addAll(ins,del,sel,shwall,Cshwall);
							stage1.show();
						}
					}
					if(f!=1)
						response.setText("Invalid Email id or Password");
				}
				catch(SQLException e)
				{
					response.setText("caught");
				}
				
			}
		});
		root.getChildren().addAll(l1,tf,l2,pf,log,response);
		stage1.setResizable(false);
		stage1.show();
	}
}