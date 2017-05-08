import com.google.gson.annotations.SerializedName;

public class Report {
	@SerializedName("client-address")
	private String clientAddress;
	
	@SerializedName("client-guid")
	private String clientGUID;
	
	@SerializedName("request-time")
	private String requestTime;
	
	@SerializedName("service-guid")
	private String serviceGUID;
	
	@SerializedName("retries-request")
	private int retriesRequest;
	
	@SerializedName("packets-requested")
	private int packetsRequested;
	
	@SerializedName("packets-serviced")
	private int packetsServiced;
	
	@SerializedName("max-hole-size")
	private int maxHoleSize;

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getClientGUID() {
		return clientGUID;
	}

	public void setClientGUID(String clientGUID) {
		this.clientGUID = clientGUID;
	}

	public String getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(String requestTime) {
		this.requestTime = requestTime;			
	}
	
	public String getServiceGUID() {
		return serviceGUID;
	}

	public void setServiceGUID(String serviceGUID) {
		this.serviceGUID = serviceGUID;
	}

	public int getRetriesRequest() {
		return retriesRequest;
	}

	public void setRetriesRequest(int retriesRequest) {
		this.retriesRequest = retriesRequest;
	}

	public int getPacketsRequested() {
		return packetsRequested;
	}

	public void setPacketsRequested(int packetsRequested) {
		this.packetsRequested = packetsRequested;
	}

	public int getPacketsServiced() {
		return packetsServiced;
	}

	public void setPacketsServiced(int packetsServiced) {
		this.packetsServiced = packetsServiced;
	}

	public int getMaxHoleSize() {
		return maxHoleSize;
	}

	public void setMaxHoleSize(int maxHoleSize) {
		this.maxHoleSize = maxHoleSize;
	}
	
	@Override
	public String toString() {
		return "Report:: clientAddress=" + this.clientAddress +
				" clientGUID=" + this.clientGUID +
				" requestTime=" + this.getRequestTime() +
				" serviceGUID=" + this.serviceGUID +
				" retriesRequest=" + this.retriesRequest +
				" packetsRequested=" + this.packetsRequested +
				" packetsServiced=" + this.packetsServiced +
				" maxHoleSize=" + this.maxHoleSize;
	}
	
}
