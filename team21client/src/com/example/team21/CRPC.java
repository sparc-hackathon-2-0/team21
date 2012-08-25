package com.example.team21;

import java.net.URL;

import org.json.rpc.client.HttpJsonRpcClientTransport;
import org.json.rpc.client.JsonRpcInvoker;


public class CRPC {
	public static Rpc getRPC(String url)  {
		Rpc rpc = null;
		try {
			HttpJsonRpcClientTransport transport = null;
			transport = new HttpJsonRpcClientTransport(new URL(url));
			JsonRpcInvoker invoker = new JsonRpcInvoker();

			rpc = invoker.get(transport, "rpc", Rpc.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rpc;
	}

}
