package com.cust.mdm.export;

import com.orchestranetworks.service.OperationException;
import com.orchestranetworks.workflow.ScriptTaskBean;
import com.orchestranetworks.workflow.ScriptTaskBeanContext;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationHome;
import com.onwbp.adaptation.AdaptationName;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.Request;
import com.onwbp.base.text.Severity;
import com.onwbp.base.text.UserMessage;
import com.orchestranetworks.instance.HomeKey;
import com.orchestranetworks.instance.Repository;
import com.orchestranetworks.ps.recordscomparison.Messages;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.service.ExportSpec;
import com.orchestranetworks.service.Procedure;
import com.orchestranetworks.service.ProcedureContext;
import com.orchestranetworks.service.ProcedureResult;
import com.orchestranetworks.service.ProgrammaticService;
import com.orchestranetworks.service.Session;
import java.io.File;




public class ExportMaxFiles extends ScriptTaskBean {
	private String dataspaceName;
	private String datasetName;
	private String tablePath;
	private String filePath;

	public final void setDataspaceName(final String dataspaceName) {
		this.dataspaceName = dataspaceName;
	}

	public final void setDatasetName(final String datasetName) {
		this.datasetName = datasetName;
	}

	public final void setTablePath(final String tablePath) {
		this.tablePath = tablePath;
	}

	public final void setFilePath(final String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void executeScript(final ScriptTaskBeanContext context) throws OperationException {
		
		final Repository repository = context.getRepository();
		
		final AdaptationHome dataspace = toDataspace(repository, this.dataspaceName);
		final Adaptation dataset = toDataset(dataspace, this.datasetName);
		
		
		String[] tablePathh = tablePath.split(",");
			String []filePathe=filePath.split(",");
			for(int j=0;j<tablePathh.length;j++)
			{
				String tableName=tablePathh[j];
				final AdaptationTable table = toTable(dataset, tableName);
				
				final File file = toFile(filePathe[j]);
				final ExportSpec exportSpec = createExportSpec(table, file);
				
				executeScript(context, dataspace, exportSpec);
			
			}	
			
	
		
	
		
	}

	private static void executeScript(final ScriptTaskBeanContext context, final AdaptationHome dataspace,
		final ExportSpec exportSpec) throws OperationException {
		final Session session = context.getSession();
		final ProgrammaticService service = ProgrammaticService.createForSession(session, dataspace);
		final ProcedureResult result = service.execute(new Procedure() {
			public void execute(final ProcedureContext procedureContext) throws Exception {
				procedureContext.doExport(exportSpec);
			}
		});
		if (result.hasFailed()) {
			final OperationException exception = result.getException();
			throw exception;
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

	private static File toFile(final String filePath) throws OperationException {
		try {
			final File file = new File(filePath);
			file.getCanonicalPath();
			return file;
		} catch (final Exception exception) {
			throw OperationException.createError("error");

		}
	}

	protected ExportSpec createExportSpec(final AdaptationTable table, final File file) {
	//	ExportImportCSVSpec();
		final ExportSpec exportSpec = new ExportSpec();
		final Request request = table.createRequest();
		exportSpec.setRequest(request);
		exportSpec.setCheckAccessRules(false);
		exportSpec.setContentIndented(true);
		exportSpec.setIncludesTechnicalData(false);
		exportSpec.setDestinationFile(file);
		return exportSpec;
	}

	/*
	 * private void ExportImportCSVSpec() { // TODO Auto-generated method stub final
	 * ExportSpec exportSpec = new ExportSpec(); exportSpec.setCSVSpec(null);
	 * 
	 * }
	 */}
