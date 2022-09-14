package com.cust.mdm.Subworkflow;

import com.cust.mdm.cust.path.CustPaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationHome;
import com.onwbp.adaptation.AdaptationName;
import com.onwbp.adaptation.XPathExpressionHelper;
import com.onwbp.base.text.Severity;
import com.onwbp.base.text.UserMessage;
import com.orchestranetworks.instance.HomeKey;
import com.orchestranetworks.instance.Repository;
import com.orchestranetworks.ps.recordscomparison.Messages;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.workflow.ProcessLauncher;
import com.orchestranetworks.workflow.SubWorkflowsCompletionContext;
import com.orchestranetworks.workflow.SubWorkflowsCreationContext;
import com.orchestranetworks.workflow.SubWorkflowsInvocationBean;
import java.math.BigDecimal;
import java.util.UUID;

public class ItemCheckSubWorkflowsInvocation extends SubWorkflowsInvocationBean {
	public static final AdaptationName STORE_DATA_OWNER_SUBWORKFLOW_MODEL_NAME = AdaptationName
			.forName("StoreDataOwnerSubworkflowModel-Solution");
	public static final AdaptationName STORE_DATA_VALIDATOR_SUBWORKFLOW_MODEL_NAME = AdaptationName
			.forName("StoreDataValidatorSubworkflowModel-Solution");
	public static final String DATASPACE_VARIABLE_NAME = "dataspace";
	public static final String DATASET_VARIABLE_NAME = "dataset";
	public static final String ITEM_VARIABLE_NAME = "item";
	public static final String ITEM_PRICE_THRESHOLD_VARIABLE_NAME = "itemPriceThreshold";

	@Override
	public void handleCreateSubWorkflows(final SubWorkflowsCreationContext context) throws OperationException {
		final String dataspaceName = context.getVariableString(DATASPACE_VARIABLE_NAME);
		final String datasetName = context.getVariableString(DATASET_VARIABLE_NAME);
		final String itemPath = context.getVariableString(ITEM_VARIABLE_NAME);
		final String itemPriceThresholdString = context.getVariableString(ITEM_PRICE_THRESHOLD_VARIABLE_NAME);
		final Repository repository = context.getRepository();
		final AdaptationHome dataspace = toDataspace(repository, dataspaceName);
		final Adaptation dataset = toDataset(dataspace, datasetName);
		final Adaptation item = toItem(dataset, itemPath);
		final BigDecimal itemPriceThreshold = toItemPriceThreshold(itemPriceThresholdString);
		final BigDecimal itemPrice = (BigDecimal) item.get(CustPaths._Root_Item._Root_Item_DefaultPrice);
		final boolean doubleChecking = itemPriceThreshold == null || itemPriceThreshold.compareTo(itemPrice) <= 0;
		handleCreateSubWorkflows(context, dataspaceName, datasetName, itemPath, doubleChecking);
	}

	private void handleCreateSubWorkflows(final SubWorkflowsCreationContext context, final String dataspaceName,
			final String datasetName, final String itemPath, final boolean doubleChecking) throws OperationException {
		{
			final ProcessLauncher launcher = context.registerSubWorkflow(STORE_DATA_OWNER_SUBWORKFLOW_MODEL_NAME,
					UUID.randomUUID().toString());
			launcher.setInputParameter(DATASPACE_VARIABLE_NAME, dataspaceName);
			launcher.setInputParameter(DATASET_VARIABLE_NAME, datasetName);
			launcher.setInputParameter(ITEM_VARIABLE_NAME, itemPath);
		}
		if (doubleChecking) {
			final ProcessLauncher launcher = context.registerSubWorkflow(STORE_DATA_VALIDATOR_SUBWORKFLOW_MODEL_NAME,
					UUID.randomUUID().toString());
			launcher.setInputParameter(DATASPACE_VARIABLE_NAME, dataspaceName);
			launcher.setInputParameter(DATASET_VARIABLE_NAME, datasetName);
			launcher.setInputParameter(ITEM_VARIABLE_NAME, itemPath);
		}
		context.launchSubWorkflows();
	}

	@Override
	public void handleCompleteAllSubWorkflows(final SubWorkflowsCompletionContext context) throws OperationException {
		// Nothing to do
	}

	private static AdaptationHome toDataspace(final Repository repository, final String dataspaceName)
			throws OperationException {
		try {
			final HomeKey dataspaceKey = HomeKey.forBranchName(dataspaceName);
			final AdaptationHome dataspace = repository.lookupHome(dataspaceKey);
			if (dataspace == null) {
				throw new IllegalArgumentException();
			}
			return dataspace;
		} catch (final Exception exception) {
			final UserMessage message = Messages.get(ItemCheckSubWorkflowsInvocation.class, Severity.ERROR,
					"TheProvidedDataspaceNameIsIncorrect{0}", dataspaceName);
			throw OperationException.createError(message);
		}
	}

	private static Adaptation toDataset(final AdaptationHome dataspace, final String datasetName)
			throws OperationException {
		try {
			final AdaptationName datasetKey = AdaptationName.forName(datasetName);
			final Adaptation dataset = dataspace.findAdaptationOrNull(datasetKey);
			if (dataset == null) {
				throw new IllegalArgumentException();
			}
			return dataset;
		} catch (final Exception exception) {
			final UserMessage message = Messages.get(ItemCheckSubWorkflowsInvocation.class, Severity.ERROR,
					"TheProvidedDatasetNameIsIncorrect{0}", datasetName);
			throw OperationException.createError(message);
		}
	}

	private static Adaptation toItem(final Adaptation dataset, final String itemPath) throws OperationException {
		try {
			final Adaptation item = XPathExpressionHelper.lookupFirstRecordMatchingXPath(dataset, itemPath);
			if (item == null) {
				throw new IllegalArgumentException();
			}
			return item;
		} catch (final Exception exception) {
			final UserMessage message = Messages.get(ItemCheckSubWorkflowsInvocation.class, Severity.ERROR,
					"TheProvidedItemPathIsIncorrect{0}", itemPath);
			throw OperationException.createError(message);
		}
	}

	private static BigDecimal toItemPriceThreshold(final String itemPriceThresholdString) throws OperationException {
		try {
			if (itemPriceThresholdString == null || itemPriceThresholdString.trim().isEmpty()) {
				return null;
			}
			return new BigDecimal(itemPriceThresholdString);
		} catch (final Exception exception) {
			final UserMessage message = Messages.get(ItemCheckSubWorkflowsInvocation.class, Severity.ERROR,
					"TheProvidedItemPriceThresholdIsIncorrect{0}", itemPriceThresholdString);
			throw OperationException.createError(message);
		}
	}
}
