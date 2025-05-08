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

package core.Persistence;

import core.Category.repositories.CategoryRepository;
import core.Customer.repositories.CustomerRepository;
import core.Figure.repositories.FigureRepository;
import core.ModelOfDrone.repositories.ModelRepository;
import core.User.repositories.ShodroneUserRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventConsumptionRepository;
import eapli.framework.infrastructure.pubsub.impl.simplepersistent.repositories.EventRecordRepository;

/**
 * The interface for the repository factory of shodrone.
 * <p>
 * This is the Abstract Factory in the Abstract Factory (GoF) pattern. Each of
 * the return types is an Abstract Product. For instance,
 * {@link}
 *  is an Abstract Product while
 * {@link}
 *  is a Concrete Product.
 * </p>
 *
 */
public interface RepositoryFactory {

    /**
     * Factory method to create a transactional context to use in the repositories
     *
     * @return a new transactional context
     */
    TransactionalContext newTransactionalContext();

    UserRepository users(TransactionalContext autoTx);

    UserRepository users();

    FigureRepository figures(TransactionalContext autoTx);

    FigureRepository figures();

    CustomerRepository customers(TransactionalContext autoTx);

    CustomerRepository customers();

    ModelRepository models(TransactionalContext autoTx);

    ModelRepository models();

    CategoryRepository categories(TransactionalContext autoTx);

    CategoryRepository categories();

    ShodroneUserRepository shodroneUsers(TransactionalContext autoTx);

    ShodroneUserRepository shodroneUsers();

    EventConsumptionRepository eventConsumption();

    EventRecordRepository eventRecord();
}
