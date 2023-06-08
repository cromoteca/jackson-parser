import { createContext } from "react";

export type ServerState = {
    server: string;
};

export const initialServerState: ServerState = {
    server: "http://localhost:3000",
};

export type ServerContextType = {
    state: ServerState;
};

export const ServerContext = createContext<ServerContextType>({
    state: initialServerState,
});

export const ServerContextProvider = ServerContext.Provider;
