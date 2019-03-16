package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Class to act as the controller for MVC design pattern
 * 
 * "Model View, Model View, Model View Controller
 *  MVC’s the paradigm for factoring your code,
 *  into functional segments so your brain does not explode.
 *  ...."
 *  
 * @author ShayneShaw
 *
 */

public class CafeController {
	
	private CafeGUI view;
	private Server slave;
	private CafeQueue line;
	
	public CafeController(CafeGUI view, Server slave, CafeQueue line){
		
		this.view = view;
		this.slave = slave;
		this.line = line;
		
	}
		
}

