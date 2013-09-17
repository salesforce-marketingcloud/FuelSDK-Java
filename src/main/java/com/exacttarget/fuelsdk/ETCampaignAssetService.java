package com.exacttarget.fuelsdk;

import com.exacttarget.fuelsdk.filter.ETFilter;
import com.exacttarget.fuelsdk.model.ETCampaignAsset;

public interface ETCampaignAssetService extends ETCrudService {

	ETServiceResponse<ETCampaignAsset> get(ETClient client, String campaignId, String assetId) throws ETSdkException ;
	
	ETServiceResponse<ETCampaignAsset> get(ETClient client, ETFilter filter) throws ETSdkException ;
	
	ETServiceResponse<ETCampaignAsset> post(ETClient client, ETCampaignAsset asset) throws ETSdkException ;
	
	ETServiceResponse<ETCampaignAsset> patch(ETClient client, ETCampaignAsset asset) throws ETSdkException ;
	
	ETServiceResponse<ETCampaignAsset> delete(ETClient client, ETCampaignAsset asset) throws ETSdkException ;
	
}
