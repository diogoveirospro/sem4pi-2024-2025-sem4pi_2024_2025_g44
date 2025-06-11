/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package inMemory.persistence;

import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Category.repositories.CategoryRepository;
import core.Customer.repositories.CustomerRepository;
import core.Daemon.reporting.proposals.repositories.DeliveryReportingRepository;
import core.Daemon.reporting.shows.repositories.ShowReportingRepository;
import core.Drone.repositories.DroneRepository;
import core.Figure.repositories.FigureRepository;
import core.Maintenance.repositories.MaintenanceTypeRepository;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.RepositoryFactory;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;
import core.ShowDesigner.repositories.ShowDesignerRepository;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.repositories.ShowRequestRepository;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.inmemory.InMemoryUserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryTransactionalContext;
import inMemory.*;
import shodrone.bootstrappers.ShodroneBootstrapper;
import core.Maintenance.repositories.MaintenanceRepository;

/**
 * @author nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

	@Override
	public TransactionalContext newTransactionalContext() {
		return new InMemoryTransactionalContext();
	}

	/**
	 * Initialize a power user so that we can login.
	 */
	@Override
	public UserRepository users(final TransactionalContext tx) {
		final var repo = new InMemoryUserRepository();
		final var repo2 = new InMemoryShodroneUserRepository();
		final var repo3 = new InMemoryShowDesignerRepository();
		final var repo4 = new InMemoryCRMCollaboratorRepository();
		final var repo5 = new InMemoryCRMManagerRepository();
		ShodroneBootstrapper.registerPowerUser(repo, repo2, repo3, repo4, repo5);
		return repo;
	}

	@Override
	public UserRepository users() {
		return users(null);
	}

	@Override
	public FigureRepository figures(TransactionalContext autoTx) {
		return new InMemoryFigureRepository();
	}

	@Override
	public FigureRepository figures() {
		return figures(null);
	}

	@Override
	public ShowDesignerRepository showDesigners(TransactionalContext autoTx) {
		return new InMemoryShowDesignerRepository();
	}

	@Override
	public ShowDesignerRepository showDesigners() {
		return showDesigners(null);
	}

	@Override
	public CustomerRepository customers(TransactionalContext autoTx) {
		return new InMemoryCustomerRepository();
	}

	@Override
	public CustomerRepository customers() {
		return customers(null);
	}

	@Override
	public ShowRequestRepository showRequest(TransactionalContext autoTx) { return new InMemoryShowRequestRepository(); }

	@Override
	public ShowRequestRepository showRequest() {
		return showRequest(null);
	}

	@Override
	public CRMCollaboratorRepository crmCollaborators(TransactionalContext autoTx) {
		return new InMemoryCRMCollaboratorRepository();
	}

	@Override
	public CRMCollaboratorRepository crmCollaborators() {
		return crmCollaborators(null);
	}

	@Override
	public ShowProposalRepository proposals(TransactionalContext autoTx) {
		return new InMemoryShowProposalRepository();
	}

	@Override
	public ShowProposalRepository proposals() {
		return proposals(null);
	}

	@Override
	public CRMManagerRepository crmManagers(TransactionalContext autoTx) {
		return new InMemoryCRMManagerRepository();
	}

	@Override
	public CRMManagerRepository crmManagers() {
		return crmManagers(null);
	}

	@Override
	public ProposalDeliveryInfoRepository deliveries(TransactionalContext autoTx) {
		return new InMemoryProposalDeliveryInfoRepository();
	}

	@Override
	public ProposalDeliveryInfoRepository deliveries() {
		return deliveries(null);
	}

	@Override
	public ShowReportingRepository shows() {
		return new InMemoryShowReportingRepository();
	}

	@Override
	public DeliveryReportingRepository deliveriesReporting() {
		return new InMemoryDeliveryReportingRepository();
	}

	@Override
	public ModelRepository models(TransactionalContext autoTx) {
		return new InMemoryModelRepository();
	}

	@Override
	public ModelRepository models() {
		return models(null);
	}

	@Override
	public DroneRepository drone(TransactionalContext autoTx) {
		return new InMemoryDroneRepository();
	}

	@Override
	public DroneRepository drone() {
		return drone(null);
	}

	@Override
	public CategoryRepository categories(TransactionalContext autoTx) {
		return new InMemoryCategoryRepository();
	}

	@Override
	public CategoryRepository categories() {
		return categories(null);
	}

	@Override
	public ShodroneUserRepository shodroneUsers(TransactionalContext autoTx) {
		return new InMemoryShodroneUserRepository();
	}

	@Override
	public ShodroneUserRepository shodroneUsers() {
		return shodroneUsers(null);
	}

	@Override
	public EventConsumptionRepository eventConsumption() {
		throw new IllegalStateException("Not implemented yet.");
	}

	@Override
	public EventRecordRepository eventRecord() {
		throw new IllegalStateException("Not implemented yet.");
	}

	@Override
	public MaintenanceRepository maintenance(TransactionalContext autoTx) {
		throw new IllegalStateException("Not implemented yet.");
	}

	@Override
	public MaintenanceRepository maintenance() {
		throw new IllegalStateException("Not implemented yet.");
	}

	@Override
	public MaintenanceTypeRepository maintenanceTypes(TransactionalContext autoTx) {
		return new InMemoryMaintenanceTypeRepository();
	}

	@Override
	public MaintenanceTypeRepository maintenanceTypes() {
		return maintenanceTypes(null);
	}

}
