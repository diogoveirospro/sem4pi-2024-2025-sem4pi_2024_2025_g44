/*
 * Copyright (c) 2013-2024 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package jpa.persistence;

import core.CRMCollaborator.repositories.CRMCollaboratorRepository;
import core.CRMManager.repositories.CRMManagerRepository;
import core.Category.repositories.CategoryRepository;
import core.Customer.repositories.CustomerRepository;
import core.Daemon.reporting.proposals.repositories.DeliveryReportingRepository;
import core.Daemon.reporting.shows.repositories.ShowReportingRepository;
import core.Drone.repositories.DroneRepository;
import core.Figure.repositories.FigureRepository;
import core.Maintenance.repositories.MaintenanceRepository;
import core.ModelOfDrone.repositories.ModelRepository;
import core.Persistence.RepositoryFactory;
import core.ProposalDeliveryInfo.repositories.ProposalDeliveryInfoRepository;
import core.ShowDesigner.repositories.ShowDesignerRepository;
import core.ShowProposal.repositories.ShowProposalRepository;
import core.ShowRequest.repositories.ShowRequestRepository;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.authz.repositories.impl.jpa.JpaAutoTxUserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa.JpaAutoTxEventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.jpa.JpaAutoTxEventRecordRepository;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;
import core.Persistence.Application;
import jpa.*;

/**
 * The repository factory for JPA repositories.
 * <p>
 * This is the concrete factory in the Abstract Factory (GoF) pattern
 * </p>
 *
 */
public class JpaRepositoryFactory implements RepositoryFactory {

	@Override
	public TransactionalContext newTransactionalContext() {
		return JpaAutoTxRepository.buildTransactionalContext(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public UserRepository users(final TransactionalContext autoTx) {
		return new JpaAutoTxUserRepository(autoTx);
	}

	@Override
	public UserRepository users() {
		return new JpaAutoTxUserRepository(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public FigureRepository figures(TransactionalContext autoTx) {
		return new JpaFigureRepository(autoTx);
	}

	@Override
	public FigureRepository figures() {
		return new JpaFigureRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public CustomerRepository customers(TransactionalContext autoTx) {
		return new JpaCustomerRepository(autoTx);
	}

	@Override
	public CustomerRepository customers() {
		return new JpaCustomerRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShowRequestRepository showRequest(TransactionalContext autoTx) {
		return new JpaShowRequestRepository(autoTx);
	}

	@Override
	public ShowRequestRepository showRequest() {
		return new JpaShowRequestRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ModelRepository models(TransactionalContext autoTx) {
		return new JpaModelRepository(autoTx);
	}

	@Override
	public ModelRepository models() {
		return new JpaModelRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public DroneRepository drone(TransactionalContext autoTx) {
		return new JpaDroneRepository(autoTx);
	}

	@Override
	public DroneRepository drone() {
		return new JpaDroneRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public CategoryRepository categories(TransactionalContext autoTx) {
		return new JpaCategoryRepository(autoTx);
	}

	@Override
	public CategoryRepository categories() {
		return new JpaCategoryRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShodroneUserRepository shodroneUsers(TransactionalContext autoTx) {
		return new JpaShodroneUserRepository(autoTx);
	}

	@Override
	public ShodroneUserRepository shodroneUsers() {
		return new JpaShodroneUserRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public EventConsumptionRepository eventConsumption() {
		return new JpaAutoTxEventConsumptionRepository(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public EventRecordRepository eventRecord() {
		return new JpaAutoTxEventRecordRepository(Application.settings().persistenceUnitName(),
				Application.settings().extendedPersistenceProperties());
	}

	@Override
	public ShowDesignerRepository showDesigners(TransactionalContext autoTx) {
		return new JpaShowDesignerRepository(autoTx);
	}

	@Override
	public ShowDesignerRepository showDesigners() {
		return new JpaShowDesignerRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public CRMCollaboratorRepository crmCollaborators (TransactionalContext autoTx) {
		return new JpaCRMCollaboratorRepository(autoTx);
	}

	@Override
	public CRMCollaboratorRepository crmCollaborators() {
		return new JpaCRMCollaboratorRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShowProposalRepository proposals(TransactionalContext autoTx) {
		return new  JpaShowProposalRepository(autoTx);
	}

	@Override
	public ShowProposalRepository proposals() {
		return new JpaShowProposalRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public CRMManagerRepository crmManagers(TransactionalContext autoTx) {
		return new JpaCRMManagerRepository(autoTx);
	}

	@Override
	public CRMManagerRepository crmManagers() {
		return new JpaCRMManagerRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ProposalDeliveryInfoRepository deliveries(TransactionalContext autoTx) {
		return new JpaProposalDeliveryInfoRepository(autoTx);
	}

	@Override
	public ProposalDeliveryInfoRepository deliveries() {
		return new JpaProposalDeliveryInfoRepository(Application.settings().persistenceUnitName());
	}

	@Override
	public ShowReportingRepository shows() {
		return new JpaShowReportingRepository();
	}

	@Override
	public DeliveryReportingRepository deliveriesReporting() {
		return new JpaDeliveryReportingRepository();
	}

	@Override
	public MaintenanceRepository maintenance() {
		return new JpaMaintenanceRepository(Application.settings().persistenceUnitName());
	}

}
