interface RegistrationInfo {
    country: string;
    email: string;
    name: string;
    phone?: string | undefined;
    terms: boolean;
}

export default RegistrationInfo;
