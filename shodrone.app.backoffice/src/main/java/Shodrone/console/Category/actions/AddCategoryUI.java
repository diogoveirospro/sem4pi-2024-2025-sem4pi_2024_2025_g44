package Shodrone.console.Category.actions;

            import Shodrone.exceptions.UserCancelledException;
            import core.Category.application.AddCategoryController;
            import core.Category.domain.ValueObjects.CategoryName;
            import core.Shared.domain.ValueObjects.Description;
            import core.User.domain.ShodroneRoles;
            import eapli.framework.infrastructure.authz.application.AuthorizationService;
            import eapli.framework.infrastructure.authz.application.AuthzRegistry;
            import shodrone.presentation.AbstractFancyUI;
            import shodrone.presentation.UtilsUI;

            import static shodrone.presentation.UtilsUI.readLineFromConsole;

            /**
             * User Interface for adding a category to the system.
             */
            public class AddCategoryUI extends AbstractFancyUI {

                private final AddCategoryController controller = new AddCategoryController();
                private final AuthorizationService authz = AuthzRegistry.authorizationService();

                /**
                 * Show the UI for adding a category.
                 *
                 * @return true if the category was added successfully, false otherwise.
                 */
                @Override
                protected boolean doShow() {
                    if (!isShowDesigner()) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAccess denied. Only Show Designers can perform this action." + UtilsUI.RESET);
                        return false;
                    }
                    try {
                        CategoryName name = enterValidName();

                        // Check if a category with the same name already exists
                        if (controller.categoryExists(name)) {
                            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nA category with this name already exists. Please choose a different name." + UtilsUI.RESET);
                            return false;
                        }

                        Description description = enterValidDescription();

                        controller.addCategory(name, description);
                        System.out.println(UtilsUI.GREEN + UtilsUI.BOLD + "\nCategory added successfully!" + UtilsUI.RESET);
                        UtilsUI.goBackAndWait();
                        return true;

                    } catch (UserCancelledException e) {
                        System.out.println(UtilsUI.YELLOW + UtilsUI.BOLD + "\nOperation cancelled by the user." + UtilsUI.RESET);
                    } catch (IllegalArgumentException e) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nError: " + e.getMessage() + UtilsUI.RESET);
                    } catch (Exception e) {
                        System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nAn unexpected error occurred: " + e.getMessage() + UtilsUI.RESET);
                    }
                    return false;
                }


                /**
                 * Display the headline for the UI.
                 * @return the headline string.
                 */
                @Override
                public String headline() {
                    return "Add Category";
                }

                /**
                 * Enter a valid name for the category.
                 * @return a CategoryName object.
                 */
                private CategoryName enterValidName() {
                    String name;
                    do {
                        try {
                            name = readLineFromConsole(UtilsUI.BOLD + "Enter the category name (or type 'cancel' to go back): " + UtilsUI.RESET);

                            if ("cancel".equalsIgnoreCase(name)) {
                                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                            }

                            return new CategoryName(name);
                        } catch (IllegalArgumentException e) {
                            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                        }
                    } while (true);
                }

                /**
                 * Enter a valid description for the category.
                 * @return a Description object.
                 */
                private Description enterValidDescription() {
                    String description;
                    do {
                        try {
                            description = readLineFromConsole(UtilsUI.BOLD + "Enter the category description (or type 'cancel' to go back): " + UtilsUI.RESET);

                            if ("cancel".equalsIgnoreCase(description)) {
                                throw new UserCancelledException(UtilsUI.YELLOW + UtilsUI.BOLD + "\nAction cancelled by user." + UtilsUI.RESET);
                            }

                            return new Description(description);
                        } catch (IllegalArgumentException e) {
                            System.out.println(UtilsUI.RED + UtilsUI.BOLD + "\nInvalid input. Please try again." + UtilsUI.RESET);
                        }
                    } while (true);
                }

                private boolean isShowDesigner() {
                    return authz.isAuthenticatedUserAuthorizedTo(ShodroneRoles.POWER_USER, ShodroneRoles.SHOWDESIGNER);
                }
            }