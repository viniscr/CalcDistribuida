package cliente.aplicacao;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import base.servidor.TipoServidor;
import cliente.OperatorClient;

public class Calculadora extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JButton btsnumericos[];
	JButton btnback, btnce, btnc;
	JButton btnoperacoes[], jdb;
	JLabel jl;
	JTextField tfresult;
	JPanel panel1, panel2, panel3;
	JDialog jd;
	double d1, d2, d3;
	boolean fracao = false;
	String operacao;
	boolean opc = false, ope = false;

	public Calculadora(String str) {

		super(str);

		btsnumericos = new JButton[10];
		jd = new JDialog(this, "Message", true);
		jdb = new JButton("OK");
		jl = new JLabel();
		jd.setLayout(new FlowLayout());
		jdb.setMargin(new Insets(5, 5, 5, 5));
		jd.add(jl);
		jd.add(jdb);
		jdb.addActionListener(this);

		for (int i = 0; i < 10; i++){
			btsnumericos[i] = new JButton(Integer.toString(i));
			btsnumericos[i].setBackground(Color.WHITE);
			btsnumericos[i].setFont(new Font("Arial", Font.BOLD, 20));
		}

		btnback = new JButton("←");
		btnback.setBackground(Color.WHITE);
		btnback.setFont(new Font("Arial", Font.BOLD, 20));
		btnce = new JButton("CE");
		btnce.setBackground(Color.WHITE);
		btnce.setFont(new Font("Arial", Font.BOLD, 20));
		btnc = new JButton("C");
		btnc.setBackground(Color.WHITE);
		btnc.setFont(new Font("Arial", Font.BOLD, 20));

		panel2 = new JPanel();
		panel2.setLayout(new FlowLayout());
		panel2.setBackground(Color.white);
		panel2.add(btnback);
		panel2.add(btnce);
		panel2.add(btnc);

		tfresult = new JTextField(15);
		panel1 = new JPanel();
		panel1.setBackground(Color.white);
		panel1.add(tfresult);
		tfresult.setEditable(false);
		tfresult.setHorizontalAlignment(JTextField.RIGHT);
		tfresult.setText("0");
		tfresult.setBackground(Color.white);
		tfresult.setFont(new Font("Arial", Font.BOLD, 22));

		btnoperacoes = new JButton[10];
		btnoperacoes[0] = new JButton("+");
		btnoperacoes[1] = new JButton("-");
		btnoperacoes[2] = new JButton("*");
		btnoperacoes[3] = new JButton("/");
		btnoperacoes[4] = new JButton(".");
		btnoperacoes[5] = new JButton("=");
		btnoperacoes[6] = new JButton("%");
		btnoperacoes[7] = new JButton("Ѵ");
		btnoperacoes[8] = new JButton("x²");
		btnoperacoes[9] = new JButton("+/-");

		for (int i = 0; i < 10; i++) {
			btnoperacoes[i].setMargin(new Insets(5, 5, 5, 5));
			btnoperacoes[i].addActionListener(this);
			btnoperacoes[i].setBackground(Color.WHITE);
			btnoperacoes[i].setFont(new Font("Arial", Font.BOLD, 20));
		}

		panel3 = new JPanel();
		panel3.setLayout(new GridLayout(4, 5, 4 , 5));
		panel3.setBackground(Color.white);
		panel3.add(btsnumericos[1]);
		panel3.add(btsnumericos[2]);
		panel3.add(btsnumericos[3]);
		panel3.add(btnoperacoes[0]);
		panel3.add(btnoperacoes[7]);
		panel3.add(btsnumericos[4]);
		panel3.add(btsnumericos[5]);
		panel3.add(btsnumericos[6]);
		panel3.add(btnoperacoes[1]);
		panel3.add(btnoperacoes[8]);
		panel3.add(btsnumericos[7]);
		panel3.add(btsnumericos[8]);
		panel3.add(btsnumericos[9]);
		panel3.add(btnoperacoes[2]);
		panel3.add(btnoperacoes[6]);
		panel3.add(btsnumericos[0]);
		panel3.add(btnoperacoes[9]);
		panel3.add(btnoperacoes[4]);
		panel3.add(btnoperacoes[3]);
		panel3.add(btnoperacoes[5]);

		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(panel1);
		c.add(panel2);
		c.add(panel3);
		c.setBackground(Color.WHITE);

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);

		for (int i = 0; i <= 6; i++){
			btnoperacoes[i].setFont(new Font("Arial", Font.BOLD, 20));
		}

		for (int i = 0; i < 10; i++) {
			btsnumericos[i].addActionListener(this);
			btsnumericos[i].setMargin(new Insets(6, 10, 6, 10));
		}

		btnce.addActionListener(this);
		btnc.addActionListener(this);
		btnback.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		String s = tfresult.getText();
		String str;

		if (!fracao){
			str = s.substring(0, s.length() - 1);
		} else {
			str = s;
		}

		if (ae.getSource() == btnback) {
			if (fracao) {

				if (str.charAt(str.length() - 1) == '.'){
					fracao = false;
				} else {
					tfresult.setText(str.substring(0, str.length() - 1));
				}
				
			} else {

				if (!str.equals("0")) {
					tfresult.setText(str.substring(0, str.length() - 1) + ".");
				}
			}

			if (tfresult.getText().equals(".")){
				tfresult.setText("0.");
			}

		} else if (ae.getSource() == btnce) {
			tfresult.setText("0.");
			fracao = false;
			opc = false;

		} else if (ae.getSource() == btnc) {
			tfresult.setText("0.");
			d1 = 0;
			d2 = 0;
			fracao = false;
			opc = false;
			ope = false;

		} else if (ae.getSource() == btnoperacoes[0]) {
			if (ope) {
				d2 = Double.parseDouble(str);
				d3 = calc(d1, d2, operacao);
				String dn = Double.toString(d3);
				if (dn.charAt(dn.length() - 1) == '0')
					dn = dn.substring(0, dn.length() - 1);
				tfresult.setText(dn);
				ope = false;
				str = dn;
			}

			d1 = Double.parseDouble(str);
			opc = true;
			operacao = "+";

		} else if (ae.getSource() == btnoperacoes[1]) {

			if (ope) {
				d2 = Double.parseDouble(str);
				d3 = calc(d1, d2, operacao);
				String dn = Double.toString(d3);
				
				if (dn.charAt(dn.length() - 1) == '0'){
					dn = dn.substring(0, dn.length() - 1);
				}
				
				tfresult.setText(dn);
				ope = false;
				str = dn;
			}

			d1 = Double.parseDouble(str);
			opc = true;
			operacao = "-";
			
		} else if (ae.getSource() == btnoperacoes[2]) {

			if (ope) {
				d2 = Double.parseDouble(str);
				d3 = calc(d1, d2, operacao);
				String dn = Double.toString(d3);
				
				if (dn.charAt(dn.length() - 1) == '0'){
					dn = dn.substring(0, dn.length() - 1);
				}
				
				tfresult.setText(dn);
				ope = false;
				str = dn;
			}

			d1 = Double.parseDouble(str);
			opc = true;
			operacao = "*";

		} else if (ae.getSource() == btnoperacoes[3]) {

			if (ope) {
				d2 = Double.parseDouble(str);
				d3 = calc(d1, d2, operacao);
				String dn = Double.toString(d3);
				
				if (dn.charAt(dn.length() - 1) == '0') {
					dn = dn.substring(0, dn.length() - 1);
				}
				
				tfresult.setText(dn);
				ope = false;
				str = dn;
			}

			d1 = Double.parseDouble(str);
			opc = true;
			operacao = "/";

		} else if (ae.getSource() == btnoperacoes[4]) {
			fracao = true;

		} else if (ae.getSource() == btnoperacoes[5]) {

			if (ope) {

				d2 = Double.parseDouble(str);
				d3 = calc(d1, d2, operacao);
				String dn = Double.toString(d3);

				System.out.println(dn);

				if (dn.charAt(dn.length() - 1) == '0') {
					dn = dn.substring(0, dn.length() - 1);
				}

				tfresult.setText(dn);
				d1 = d3;
				opc = false;
				ope = false;
			}

		} else if (ae.getSource() == btnoperacoes[6]) {

				double temp = Double.parseDouble(tfresult.getText());
				String tmp = calc(temp, 0d, "%") + "";
				
				if (tmp.charAt(tmp.length() - 1) == '0'){
					tmp = tmp.substring(0, tmp.length() - 1);
				}
				
				tfresult.setText(tmp);

		} else if (ae.getSource() == btnoperacoes[7]) {

			double temp = Double.parseDouble(tfresult.getText());
			String tmp = calc(temp, 0d, "Ѵ") + "";
			
			if (tmp.charAt(tmp.length() - 1) == '0'){
				tmp = tmp.substring(0, tmp.length() - 1);
			}
			
			tfresult.setText(tmp);

		} else if (ae.getSource() == btnoperacoes[8]) {
			double temp = Double.parseDouble(tfresult.getText());
			String tmp = calc(temp, 0d, "x²") + "";
			
			if (tmp.charAt(tmp.length() - 1) == '0'){
				tmp = tmp.substring(0, tmp.length() - 1);
			}
			
			tfresult.setText(tmp);

		} else if (ae.getSource() == btnoperacoes[9]) {
			double temp = Double.parseDouble(tfresult.getText());
			if (temp != 0) {
				String tmp = Double.toString(-1 * temp);
				
				if (tmp.charAt(tmp.length() - 1) == '0'){
					tmp = tmp.substring(0, tmp.length() - 1);
				}
				
				tfresult.setText(tmp);
				
			} else {
				tfresult.setText("0.");
			}

		} else if (ae.getSource() == jdb) {
			jd.setVisible(false);

		} else {
			if (opc) {
				tfresult.setText("0.");
				fracao = false;
				str = "0";
				ope = true;
				opc = false;
			}

			if (fracao){
				tfresult.setText(str + ae.getActionCommand());
			}
			
			else {

				if (str.equals("0")) {
					tfresult.setText("");
					tfresult.setText(ae.getActionCommand() + ".");
				} else {
					tfresult.setText(str + ae.getActionCommand() + ".");
				}
			}
		}
	}

	public Double calc(double a, double b, String ch) {
		
		System.out.println("" + a + " " + b + " " + ch);
		
		Double result = 0d;
		
		String op = "";

		switch (ch) {
		case "+":
			op = "ADD";
			break;
		case "-":
			op = "SUB";
			break;
		case "*":
			op = "MUL";
			break;
		case "/":
			op = "DIV";
			break;
		case "x²":
			op = "POTE";
			break;
		case "%":
			op = "PORC";
			break;
		case "Ѵ":
			op = "RAIZ";
			break;
		}

		List<Double> params = new ArrayList<Double>();
		params.add(a);
		params.add(b);

		TipoServidor tipoServidor = TipoServidor.valueOf(op);
		OperatorClient client = new OperatorClient (params, tipoServidor);
		
		try {
			result = client.getValue();
			System.out.println("Resultado: " + result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void main(String[] args) {
		Calculadora c = new Calculadora("Calculadora");
		c.setSize(300, 330);
		c.setVisible(true);
	}
}