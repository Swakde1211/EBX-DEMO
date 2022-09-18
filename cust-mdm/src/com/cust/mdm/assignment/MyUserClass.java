package com.cust.mdm.assignment;

import com.cust.mdm.CustAssign.Mypaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationHome;
import com.onwbp.adaptation.AdaptationName;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.RequestResult;
import com.orchestranetworks.instance.HomeKey;
import com.orchestranetworks.instance.Repository;
import com.orchestranetworks.ps.procedure.ModifyValuesProcedure;
import com.orchestranetworks.ps.project.path.ProjectPathCapable;
import com.orchestranetworks.ps.project.path.ProjectPathConfig;
import com.orchestranetworks.ps.workflow.WorkflowUtilities;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.workflow.ScriptTask;
import com.orchestranetworks.workflow.ScriptTaskContext;
import com.orchestranetworks.workflow.UserTask;
import com.orchestranetworks.workflow.UserTaskCreationContext;

public class MyUserClass extends ScriptTask implements ProjectPathCapable {

	@Override
	public void executeScript(ScriptTaskContext context) throws OperationException {
		// TODO Auto-generated method stub
		String cvariable=context.getVariableString("steward");
		
	try {
		String record = context.getVariableString("item");
		AdaptationHome dataspace = toDataspace(context.getRepository(),context.getVariableString("dataspace"));
		Adaptation dataset = toDataset(dataspace, context.getVariableString("dataset"));
		AdaptationTable table = toTable(dataset, context.getVariableString("itemTable"));
		String id = record.substring(20, record.length() - 1);
		String predicate = Mypaths._Root_Customer._Root_Customer_ID.format() + " = '"+id+"'";
		RequestResult rr = table.createRequestResult(predicate);
		Adaptation record1 = rr.nextAdaptation();
		ModifyValuesProcedure mvp=new ModifyValuesProcedure(record1);
		mvp.setValue(Mypaths._Root_Customer._Root_Customer_CustomerMain_UserID, cvariable);
		mvp.setValue(Mypaths._Root_Customer._Root_Customer_CustomerMain_Type, "Updated");
		mvp.execute(context.getSession(), dataspace);
		}
		catch(NullPointerException e)
        {
            System.out.print("NullPointerException Caught");
        }
		
		
		
		
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
			throw OperationException.createError("error");
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
			throw OperationException.createError("error");
		}
	}
	private static AdaptationTable toTable(final Adaptation dataset, final String tablePath) throws OperationException {
		try {
			final AdaptationTable table = dataset.getTable(Path.parse(tablePath));
			return table;
		} catch (final Exception exception) {
			throw OperationException.createError("error");
		}
	}

	@Override
	public ProjectPathConfig getProjectPathConfig() {
		// TODO Auto-generated method stub
		return null;
	}
 

}
