package com.exacttarget.fuelsdk.rest;

import com.exacttarget.fuelsdk.ETCampaignAssetService;
import com.exacttarget.fuelsdk.ETClient;
import com.exacttarget.fuelsdk.ETSdkException;
import com.exacttarget.fuelsdk.ETServiceResponse;
import com.exacttarget.fuelsdk.annotations.InternalRestType;
import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaignAsset;
import com.exacttarget.fuelsdk.model.ETObject;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ETCampaignAssetServiceImpl extends ETCrudServiceImpl implements ETCampaignAssetService 
{
	public ETServiceResponse<ETCampaignAsset> get(ETClient client, String campaignId, String assetId) throws ETSdkException 
	{
		ETRestConnection connection = client.getRESTConnection();
		
		InternalRestType typeAnnotation = ETCampaignAsset.class.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }
		
		StringBuilder path = new StringBuilder(buildPath(typeAnnotation.restPath(), client.getAccessToken(), campaignId));
		
		if( assetId != null )
			path.insert(path.indexOf("?access_token"), "/" + assetId);
		
		String json = connection.get(path.toString());
		
		return createResponseETObject(ETCampaignAsset.class, json);
	}
	
	public ETServiceResponse<ETCampaignAsset> get(ETClient client, ETFilter filter) throws ETSdkException {
		return super.get(client, ETCampaignAsset.class, filter);
	}
	
	public ETServiceResponse<ETCampaignAsset> post(ETClient client, ETCampaignAsset asset) throws ETSdkException {
		return super.post(client, asset);
	}

	public ETServiceResponse<ETCampaignAsset> patch(ETClient client, ETCampaignAsset asset) throws ETSdkException {
		return super.patch(client, asset);
	}

	@SuppressWarnings("unchecked")
	public ETServiceResponse<ETCampaignAsset> delete(ETClient client, ETCampaignAsset asset) 
		throws ETSdkException 
	{
		Class<ETCampaignAsset> type = (Class<ETCampaignAsset>) asset.getClass();
		
		InternalRestType typeAnnotation = (InternalRestType) type.getAnnotation(InternalRestType.class);
		
		if(typeAnnotation == null) {
            throw new ETSdkException("The type specified does not wrap an internal ET APIObject.");
        }

		ETRestConnection connection = client.getRESTConnection();
		
		String restPath = typeAnnotation.restPath();
		String accessToken = client.getAccessToken();
		
		StringBuilder path = new StringBuilder(buildPath(restPath, accessToken, asset.getCampaignId()));
		
		path.insert(path.indexOf("?access_token"), "/" + asset.getId());
		
		String json = connection.delete(path.toString());
		
		return createResponseETObject(type, json);
	}

	@Override
	protected String buildPath(String restPath, String accessToken, String id) 
	{
		StringBuilder sb = new StringBuilder(super.buildPath(restPath, accessToken, null));

		sb.replace(sb.indexOf("{"), sb.indexOf("}")+1, id);
		
		return sb.toString();
	}

	@Override
	protected <T extends ETObject> JsonObject createRequest(T object, Class<T> type) throws ETSdkException {
		
		ETCampaignAsset asset = (ETCampaignAsset)object;
		
		JsonObject root = new JsonObject();
		
		JsonArray ids = new JsonArray();
		
		ids.add(new JsonPrimitive(asset.getItemID()));
		
		root.add("ids", ids);
		
		root.addProperty("type", asset.getType());
		
		return root;
	}

	@Override
	protected <T extends ETObject> ETServiceResponse<T> createResponseETObject(Class<T> type, String json) throws ETSdkException {
		return super.createResponseETObject(type, json);
	}
}
