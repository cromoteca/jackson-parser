import { Button, Notification, TextField } from '@vaadin/react-components';
import { useState } from 'react';
import HelloReactEndpoint from "Frontend/generated/com/example/application/endpoints/helloreact/HelloReactEndpoint.js";
import SpringEndpoint from 'Frontend/generated/com/cromoteca/samples/spring/SpringEndpoint';

export default function HelloReactView() {
  const [name, setName] = useState('');

  return (
    <>
      <section className="flex p-m gap-m items-end">
        <TextField
          label="Your name"
          onValueChanged={(e) => {
            setName(e.detail.value);
          }}
        />
        <Button
          onClick={async () => {
            const drawer = await HelloReactEndpoint.getDrawer();
            console.log(drawer, typeof drawer.date);
            const response = await HelloReactEndpoint.verifyDrawer(drawer);
            Notification.show(`Drawer: ${response}`);

            Notification.show(await SpringEndpoint.hello());
          }}
        >
          Say hello
        </Button>
      </section>
    </>
  );
}
