package edu.oakland.csi5450.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.oakland.csi5450.bean.Home;
import edu.oakland.csi5450.bean.Sale;
import edu.oakland.csi5450.repository.AgentDao;
import edu.oakland.csi5450.repository.HomeDao;
import edu.oakland.csi5450.repository.HomeOwnerDao;
import edu.oakland.csi5450.repository.SaleDao;

@Service
public class SaleService {
	@Autowired
	SaleDao saleDao;

	@Autowired
	HomeDao homeDao;

	@Autowired
	AgentDao agentDao;

	@Autowired
	HomeOwnerDao homeOwnerDao;

	@Transactional
	public String sellHome(Sale sale) {
		Home home = homeDao.getById(sale.getHomeId());
		if (home == null)
			return "Sale cannot be completed. Home does not exist";
		if (!home.getIsForSale())
			return "Sale cannot be completed. Home is not for sale";
		if (homeOwnerDao.getHomeOwnerById(sale.getOwnerId()) == null)
			return "Sale cannot be completed. Owner does not exist";
		if (!agentDao.doesAgentCompanyMappingExist(sale.getAgentId(), sale.getCompanyId()))
			return "Sale cannot be completed. Agent does not work with this company";
		Sale mostRecentSale = saleDao.getMostRecentSale(sale.getHomeId());
		if (mostRecentSale != null && !mostRecentSale.getSaleDate().before(sale.getSaleDate())) {
			return "Sale cannot be completed. Another sale of this house has already been made on or after the given date";
		}

		saleDao.addSale(sale);
		homeDao.updateHomeFromSale(home.getHomeId(), sale.getOwnerId());

		return null;
	}

}
