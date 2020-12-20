package com.powersi.ssm.biz.medicare.common.service;

import com.powersi.biz.medicare.comm.service.HttpPostService;

@org.springframework.stereotype.Service
public class HttpPostServiceImpl implements HttpPostService {

	private static final long serialVersionUID = 1L;
	private String url = null;
	private int timeout = 600 * 1000;

	@Override
	public String httpPost(String xml) {
		return httpPost(getUrl(), xml, getTimeout());
	}

	@Override
	public String httpPost(String url, String xml) {
		return httpPost(url, xml, getTimeout());
	}

	@SuppressWarnings("deprecation")
	@Override
	public String httpPost(String strUrl, String strXml, int intTimeout) {
		String charset = "utf-8";
		String callurl = strUrl;
		String sedxml = strXml;
		int litimeout = intTimeout;
		org.apache.http.impl.DefaultHttpClientConnection defaultHttpClientConnection = new org.apache.http.impl.DefaultHttpClientConnection();
		try {
			java.net.URL url = new java.net.URL(callurl);
			int liport = url.getPort();
			if (liport <= 0) {
				liport = url.getDefaultPort();
			}
			String uri = null;
			if (!org.springframework.util.StringUtils.hasLength(url.getQuery())) {
				uri = url.getPath();
			} else {
				uri = url.getPath() + "?" + url.getQuery();
			}
			org.apache.http.params.HttpParams httpParams = new org.apache.http.params.BasicHttpParams();
			org.apache.http.params.HttpProtocolParams.setVersion(httpParams, org.apache.http.HttpVersion.HTTP_1_1);
			org.apache.http.params.HttpProtocolParams.setUseExpectContinue(httpParams, true);
			org.apache.http.params.HttpConnectionParams.setSoTimeout(httpParams, litimeout);
			org.apache.http.protocol.BasicHttpProcessor basicHttpProcessor = new org.apache.http.protocol.BasicHttpProcessor();
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestContent());
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestTargetHost());
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestConnControl());
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestUserAgent());
			org.apache.http.protocol.HttpContext httpContext = new org.apache.http.protocol.BasicHttpContext(null);
			org.apache.http.HttpHost httpHost = new org.apache.http.HttpHost(url.getHost(), liport, url.getProtocol());
			org.apache.http.ConnectionReuseStrategy connectionReuseStrategy = new org.apache.http.impl.DefaultConnectionReuseStrategy();
			httpContext.setAttribute(org.apache.http.protocol.ExecutionContext.HTTP_CONNECTION,
					defaultHttpClientConnection);
			httpContext.setAttribute(org.apache.http.protocol.ExecutionContext.HTTP_TARGET_HOST, httpHost);
			org.apache.http.HttpEntity requestBody = new org.apache.http.entity.StringEntity(sedxml, charset);
			if (!defaultHttpClientConnection.isOpen()) {
				java.net.Socket socket = new java.net.Socket(httpHost.getHostName(), httpHost.getPort());
				defaultHttpClientConnection.bind(socket, httpParams);
			}
			org.apache.http.message.BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest = new org.apache.http.message.BasicHttpEntityEnclosingRequest(
					"POST", uri);
			basicHttpEntityEnclosingRequest.setEntity(requestBody);
			basicHttpEntityEnclosingRequest.setParams(httpParams);
			basicHttpEntityEnclosingRequest.setHeader("Content-Type", "text/xml; charset=" + charset);
			org.apache.http.protocol.HttpRequestExecutor httpRequestExecutor = new org.apache.http.protocol.HttpRequestExecutor();
			httpRequestExecutor.preProcess(basicHttpEntityEnclosingRequest, basicHttpProcessor, httpContext);
			org.apache.http.HttpResponse httpResponse = httpRequestExecutor.execute(basicHttpEntityEnclosingRequest,
					defaultHttpClientConnection, httpContext);
			httpResponse.setParams(httpParams);
			httpRequestExecutor.postProcess(httpResponse, basicHttpProcessor, httpContext);
			org.apache.http.StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() != org.apache.http.HttpStatus.SC_OK) {
				StringBuffer errorReason = new StringBuffer();
				errorReason.append("远程调用");
				errorReason.append(callurl);
				errorReason.append("出错：");
				String errStr = org.apache.http.util.EntityUtils.toString(httpResponse.getEntity());
				if (org.springframework.util.StringUtils.hasLength(errStr)) {
					errorReason.append(errStr);
				} else {
					errorReason.append(statusLine.getReasonPhrase());
				}
				errorReason.append("(").append(statusLine.getStatusCode()).append(")");
				throw new RuntimeException(errorReason.toString());
			}
			// 获取返回
			String recvStr = "";
			recvStr = org.apache.http.util.EntityUtils.toString(httpResponse.getEntity());
			if (!connectionReuseStrategy.keepAlive(httpResponse, httpContext)) {
				defaultHttpClientConnection.close();
			}
			return recvStr;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				defaultHttpClientConnection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void httpPost() {
		String charset = "utf-8";
		String callurl = "http://172.18.100.39:8080/hygeia_esb/api/call.action";
		String sedxml = "<?xml version=\"1.0\" encoding=\"utf-8\"?><program><function_id>sys0001</function_id><userid>002002</userid><password>123456</password></program>";
		int litimeout = 60 * 1000;
		org.apache.http.impl.DefaultHttpClientConnection defaultHttpClientConnection = new org.apache.http.impl.DefaultHttpClientConnection();
		try {
			java.net.URL url = new java.net.URL(callurl);
			int liport = url.getPort();
			if (liport <= 0) {
				liport = url.getDefaultPort();
			}
			String uri = null;
			if (!org.springframework.util.StringUtils.hasLength(url.getQuery())) {
				uri = url.getPath();
			} else {
				uri = url.getPath() + "?" + url.getQuery();
			}
			org.apache.http.params.HttpParams httpParams = new org.apache.http.params.BasicHttpParams();
			org.apache.http.params.HttpProtocolParams.setVersion(httpParams, org.apache.http.HttpVersion.HTTP_1_1);
			org.apache.http.params.HttpProtocolParams.setUseExpectContinue(httpParams, true);
			org.apache.http.params.HttpConnectionParams.setSoTimeout(httpParams, litimeout);
			org.apache.http.protocol.BasicHttpProcessor basicHttpProcessor = new org.apache.http.protocol.BasicHttpProcessor();
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestContent());
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestTargetHost());
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestConnControl());
			basicHttpProcessor.addInterceptor(new org.apache.http.protocol.RequestUserAgent());
			org.apache.http.protocol.HttpContext httpContext = new org.apache.http.protocol.BasicHttpContext(null);
			org.apache.http.HttpHost httpHost = new org.apache.http.HttpHost(url.getHost(), liport, url.getProtocol());
			org.apache.http.ConnectionReuseStrategy connectionReuseStrategy = new org.apache.http.impl.DefaultConnectionReuseStrategy();
			httpContext.setAttribute(org.apache.http.protocol.ExecutionContext.HTTP_CONNECTION,
					defaultHttpClientConnection);
			httpContext.setAttribute(org.apache.http.protocol.ExecutionContext.HTTP_TARGET_HOST, httpHost);
			org.apache.http.HttpEntity requestBody = new org.apache.http.entity.StringEntity(sedxml, charset);
			if (!defaultHttpClientConnection.isOpen()) {
				java.net.Socket socket = new java.net.Socket(httpHost.getHostName(), httpHost.getPort());
				defaultHttpClientConnection.bind(socket, httpParams);
			}
			org.apache.http.message.BasicHttpEntityEnclosingRequest basicHttpEntityEnclosingRequest = new org.apache.http.message.BasicHttpEntityEnclosingRequest(
					"POST", uri);
			basicHttpEntityEnclosingRequest.setEntity(requestBody);
			basicHttpEntityEnclosingRequest.setParams(httpParams);
			basicHttpEntityEnclosingRequest.setHeader("Content-Type", "text/xml; charset=" + charset);
			org.apache.http.protocol.HttpRequestExecutor httpRequestExecutor = new org.apache.http.protocol.HttpRequestExecutor();
			httpRequestExecutor.preProcess(basicHttpEntityEnclosingRequest, basicHttpProcessor, httpContext);
			org.apache.http.HttpResponse httpResponse = httpRequestExecutor.execute(basicHttpEntityEnclosingRequest,
					defaultHttpClientConnection, httpContext);
			httpResponse.setParams(httpParams);
			httpRequestExecutor.postProcess(httpResponse, basicHttpProcessor, httpContext);
			org.apache.http.StatusLine statusLine = httpResponse.getStatusLine();
			if (statusLine.getStatusCode() != org.apache.http.HttpStatus.SC_OK) {
				StringBuffer errorReason = new StringBuffer();
				errorReason.append("远程调用");
				errorReason.append(callurl);
				errorReason.append("出错：");
				String errStr = org.apache.http.util.EntityUtils.toString(httpResponse.getEntity());
				if (org.springframework.util.StringUtils.hasLength(errStr)) {
					errorReason.append(errStr);
				} else {
					errorReason.append(statusLine.getReasonPhrase());
				}
				errorReason.append("(").append(statusLine.getStatusCode()).append(")");
				throw new RuntimeException(errorReason.toString());
			}
			// 获取返回
			String recvStr = "";
			recvStr = org.apache.http.util.EntityUtils.toString(httpResponse.getEntity());
			if (!connectionReuseStrategy.keepAlive(httpResponse, httpContext)) {
				defaultHttpClientConnection.close();
			}
			System.out.println(recvStr);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				defaultHttpClientConnection.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
