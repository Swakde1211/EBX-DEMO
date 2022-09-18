package com.cust.mdm.assignment;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.cust.mdm.CustAssign.Mypaths;
import com.onwbp.adaptation.Adaptation;
import com.onwbp.adaptation.AdaptationTable;
import com.onwbp.adaptation.RequestResult;
import com.orchestranetworks.schema.Path;
import com.orchestranetworks.ui.UIButtonSpecNavigation;
import com.orchestranetworks.ui.UICSSClasses;
import com.orchestranetworks.ui.selection.TableViewEntitySelection;
import com.orchestranetworks.userservice.UserService;
import com.orchestranetworks.userservice.UserServiceDisplayConfigurator;
import com.orchestranetworks.userservice.UserServiceEventOutcome;
import com.orchestranetworks.userservice.UserServiceObjectContextBuilder;
import com.orchestranetworks.userservice.UserServicePaneContext;
import com.orchestranetworks.userservice.UserServicePaneWriter;
import com.orchestranetworks.userservice.UserServiceProcessEventOutcomeContext;
import com.orchestranetworks.userservice.UserServiceSetupDisplayContext;
import com.orchestranetworks.userservice.UserServiceSetupObjectContext;
import com.orchestranetworks.userservice.UserServiceValidateContext;

public class CountRecordService implements UserService<TableViewEntitySelection>

{
	private int count;

	@Override
	public UserServiceEventOutcome processEventOutcome(
			UserServiceProcessEventOutcomeContext<TableViewEntitySelection> aContext,
			UserServiceEventOutcome anEventOutcome) {
		// TODO Auto-generated method stub

		return anEventOutcome;
	}

	@Override
	public void setupDisplay(UserServiceSetupDisplayContext<TableViewEntitySelection> context,
			UserServiceDisplayConfigurator aConfigurator) {

		// TODO Auto-generated method stub
		this.count = getCount(context);

		UIButtonSpecNavigation closeButton = aConfigurator.newCloseButton();
		closeButton.setDefaultButton(true);
		aConfigurator.setLeftButtons(closeButton);
		// Set content callback
		aConfigurator.setContent(this::printUpdatedRecordsCount);

	}

	private void printUpdatedRecordsCount(UserServicePaneContext aPaneContext, UserServicePaneWriter aWriter) {
		// Display Hello World!
		aWriter.add("<div ");
		aWriter.addSafeAttribute("class", UICSSClasses.CONTAINER_WITH_TEXT_PADDING);
		aWriter.add(">");
		aWriter.add("Records modified are: " + count);
		aWriter.add("</div>");
	}

	@Override
	public void setupObjectContext(UserServiceSetupObjectContext<TableViewEntitySelection> aContext,
			UserServiceObjectContextBuilder aBuilder) {
		// TODO Auto-generated method stub

	}

	private int getCount(UserServiceSetupDisplayContext<TableViewEntitySelection> context) {

		AdaptationTable history = context.getEntitySelection().getTable().getHistory();
		String idPath = "./ID";
		Path operationsPath = Path.parse("./ebx-technical/op");
		String query = "./ebx-technical/op = 'U'";
		Set<Integer> set = new HashSet<>();
		RequestResult UpdatedRecords = history.createRequestResult(query);
		Adaptation record = UpdatedRecords.nextAdaptation();
		int size = UpdatedRecords.getSize();
		int count = 0;
		while (record != null) {
			int id = (Integer) record.get(Path.parse("./ID"));
			int trans_id = (Integer) record.get(Path.parse("/ebx-technical/tx_id"));
			if (set.contains(id)) {
				record = UpdatedRecords.nextAdaptation();
				continue;
			}
			set.add(id);
			String query1 = "./ebx-technical/op = 'D' and ./ID  =  '" + id + "'";
			RequestResult DeletedRecords = history.createRequestResult(query1);
			Adaptation record1 = DeletedRecords.nextAdaptation();
			if (record1 == null) {
				count = count + 1;
			} else {
				String query2 = "./ebx-technical/op = 'C' and ./ID  =  '" + id + "'";
				RequestResult CreatedRecords = history.createRequestResult(query2);
				Adaptation record2 = CreatedRecords.nextAdaptation();
				Integer tras_id1 = (Integer) record.get(Path.parse("/ebx-technical/tx_id"));
				Integer tras_id2 = Integer.MIN_VALUE;
				Integer tras_id3 = Integer.MIN_VALUE;
				while (record1 != null) {
					int temp = (Integer) record1.get(Path.parse("/ebx-technical/tx_id"));
					tras_id2 = Math.max(tras_id2, temp);
					record1 = DeletedRecords.nextAdaptation();
				}
				while (record2 != null) {
					int temp = (Integer) record2.get(Path.parse("/ebx-technical/tx_id"));
					tras_id3 = Math.max(tras_id3, temp);
					record2 = CreatedRecords.nextAdaptation();
				}
				if (tras_id2 > tras_id3) {
					continue;
				}
				if (tras_id1 > tras_id3) {
					count = count + 1;
				}
			}
			record = UpdatedRecords.nextAdaptation();
		}
		return count;
	}

	@Override
	public void validate(UserServiceValidateContext<TableViewEntitySelection> aContext) {
		// TODO Auto-generated method stub

	}

}
