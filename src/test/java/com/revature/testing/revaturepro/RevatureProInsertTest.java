package com.revature.testing.revaturepro;

import com.revature.assignforce.beans.Batch;
import com.revature.assignforce.beans.revaturepro.RevatureProBatchDTO;
import com.revature.assignforce.beans.revaturepro.RevatureProData;
import com.revature.assignforce.config.ProjectServiceProviderConfig;
import com.revature.assignforce.service.RevatureProService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes={ RevatureProService.class, Batch.class, ProjectServiceProviderConfig.class})

public class RevatureProInsertTest {

	@Autowired
	private RevatureProService revatureProService;

	@Autowired
	private Batch batch;

	@Before
	public void setUp() throws Exception {

		List<RevatureProData> dataList = new ArrayList<RevatureProData>();

		RevatureProData data1 = new RevatureProData();

		data1.setSalesforceId("5");
		data1.setName("Tom");
		data1.setSkill("Hibernate");
		data1.setStartDate(LocalDate.of(2018, Month.JANUARY, 5).toString());
		data1.setEndDate(LocalDate.of(2018, Month.APRIL, 5).toString());
		data1.setLocation("000040404");
		dataList.add(data1);

		RevatureProBatchDTO dto = new RevatureProBatchDTO();
		dto.setStatusCode(210);


		dto.setData(dataList);

		List<RevatureProBatchDTO> dtoList = new ArrayList<RevatureProBatchDTO>();
		dtoList.add(dto);

		System.out.println(dtoList.get(0).getStatusCode());
		System.out.println(dtoList.get(0).getData().get(0).getName());
		System.out.println(dtoList.get(0).getData().get(0).getLocation());
		System.out.println("TESTTTT");
		revatureProService.setAllBatches(dtoList);

		revatureProService.RevatureProDatabaseInsert();

	}

	@Test
	public void revatureProH2InsertTest() throws Exception {

		List<RevatureProData> dataList = new ArrayList<RevatureProData>();

		RevatureProData data = new RevatureProData();

		data.setSalesforceId("1");
		data.setName("Emerson");
		data.setSkill("Java");
		data.setStartDate(LocalDate.of(2018, Month.JANUARY, 5).toString());
		data.setEndDate(LocalDate.of(2018, Month.APRIL, 5).toString());
		data.setLocation("000040404");
		dataList.add(data);

		RevatureProBatchDTO dto = new RevatureProBatchDTO();
		dto.setStatusCode(210);

		dto.setData(dataList);

		List<RevatureProBatchDTO> dtoList = new ArrayList<RevatureProBatchDTO>();
		dtoList.add(dto);

		System.out.println(dtoList.get(0).getStatusCode());
		System.out.println(dtoList.get(0).getData().get(0).getName());

		System.out.println("ACTUAL TEST - Emerson");
		revatureProService.setAllBatches(dtoList);

		revatureProService.RevatureProDatabaseInsert();

		assertTrue("The list that was returned was not of size 2", revatureProService.getRDSBatchesOld().size() == 2); }

}
