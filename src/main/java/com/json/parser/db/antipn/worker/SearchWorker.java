package com.json.parser.db.antipn.worker;

import com.json.parser.db.antipn.dto.CustomerDto;
import com.json.parser.db.antipn.models.OutputSearchJsonObject;
import com.json.parser.db.antipn.models.searching.*;
import com.json.parser.db.antipn.services.CommonService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchWorker<T> {

    private final CommonService commonService;

    public SearchWorker(CommonService commonService) {
        this.commonService = commonService;
    }


    public OutputSearchJsonObject createJsonObject(Search criteria, List<CustomerDto> result) {
        OutputSearchJsonObject outputSearchJsonObject = new OutputSearchJsonObject();
        outputSearchJsonObject.setCriteria(criteria);
        outputSearchJsonObject.setResults(result);

        return outputSearchJsonObject;
    }

    public List<OutputSearchJsonObject> preparingOutputData(List<? extends Search> inputCriterias) {

        List<OutputSearchJsonObject> outputJsonObjects = new ArrayList<>();

        for (Search searchCriteria : inputCriterias) {

            switch (searchCriteria.getSearchType()) {

                case SEARCH_CUSTOMER_BY_LASTNAME:
                    SearchByLastName searchOne = (SearchByLastName) searchCriteria;

                    List<CustomerDto> customerDtos = commonService.searchCustomerByLastName(searchOne.getLastName());

                    outputJsonObjects.add(createJsonObject(searchOne, customerDtos));

                    break;


                case SEARCH_PRODUCT_BY_NAME_AND_MIN_QUANTITY:

                    SearchByProductNameAndQuantity searchTwo = (SearchByProductNameAndQuantity) searchCriteria;

                    List<CustomerDto> customerDtos1 = commonService.searchProductNameAndMinTimes(searchTwo.getProductName(), searchTwo.getMinTimes().longValue());

                    outputJsonObjects.add(createJsonObject(searchTwo, customerDtos1));

                    break;

                case SEARCH_MIN_EXPENSES_AND_MAX_EXPENSES:

                    SearchByMinAndMaxProductPrice searchThree = (SearchByMinAndMaxProductPrice) searchCriteria;

                    List<CustomerDto> customerDtos2 = commonService.searchCustomersWithPurchasesBeetwenMinMax(searchThree.getMinExpenses().longValue(), searchThree.getMaxExpenses().longValue());

                    outputJsonObjects.add(createJsonObject(searchThree, customerDtos2));

                    break;

                case SEARCH_BAD_CUSTOMER:
                    SearchBadCustomers searchFour = (SearchBadCustomers) searchCriteria;

                    List<CustomerDto> customerDtos3 = commonService.searchBadCustomers(searchFour.getBadCustomers().longValue());

                    outputJsonObjects.add(createJsonObject(searchFour, customerDtos3));

            }

        }

        return outputJsonObjects;
    }
}
