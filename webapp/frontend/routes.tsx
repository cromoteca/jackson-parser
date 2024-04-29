import { createBrowserRouter, IndexRouteObject, NonIndexRouteObject, useMatches } from 'react-router-dom';
import MainLayout from 'Frontend/views/MainLayout.js';
import AboutView from 'Frontend/views/about/AboutView.js';
import HelloReactView from 'Frontend/views/helloreact/HelloReactView.js';
import LoadersEndpoint from 'Frontend/generated/com/cromoteca/samples/endpoints/LoadersEndpoint.js';
import ReactiveView from 'Frontend/views/reactive/ReactiveView.js';
import RegistrationView from 'Frontend/views/helloform/RegistrationView.js';
import UserView from 'Frontend/views/user/UserView.js';
import UserListView from 'Frontend/views/user/UserListView.js';

export type MenuProps = Readonly<{
  icon?: string;
  title?: string;
}>;

export type ViewMeta = Readonly<{ handle?: MenuProps }>;

type Override<T, E> = Omit<T, keyof E> & E;

export type IndexViewRouteObject = Override<IndexRouteObject, ViewMeta>;
export type NonIndexViewRouteObject = Override<
  Override<NonIndexRouteObject, ViewMeta>,
  {
    children?: ViewRouteObject[];
  }
>;
export type ViewRouteObject = IndexViewRouteObject | NonIndexViewRouteObject;

type RouteMatch = ReturnType<typeof useMatches> extends (infer T)[] ? T : never;

export type ViewRouteMatch = Readonly<Override<RouteMatch, ViewMeta>>;

export const useViewMatches = useMatches as () => readonly ViewRouteMatch[];

export const routes: readonly ViewRouteObject[] = [
  {
    element: <MainLayout />,
    handle: { icon: 'null', title: 'Main' },
    children: [
      { path: '/', element: <HelloReactView />, handle: { icon: 'globe-solid', title: 'Hello React' } },
      { path: '/about', element: <AboutView />, handle: { icon: 'info-solid', title: 'About' } },
      { path: '/regisration', element: <RegistrationView />, handle: { icon: 'pen-solid', title: 'Registration' } },
      { path: '/reactive', element: <ReactiveView />, handle: { icon: 'phone-solid', title: 'Reactive' } },
      {
        path: '/users',
        element: <UserListView />,
        handle: { icon: 'user-solid', title: 'Users' },
        loader: LoadersEndpoint.getUsers,
      },
      {
        path: '/user/:id',
        element: <UserView />,
        loader: LoadersEndpoint.getUser,
      },
    ],
  },
];

const router = createBrowserRouter([...routes]);
export default router;
