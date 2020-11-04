# Linguist

Minimalistic java i18n library

## Features

- Small and no dependencies
- Custom string templates
- Custom output format
- `gettext` compatible

## Templating language syntax

```
// Plain strings
"some random string"

// Component invokation
"{component}"

// Components can have default slots...
"{component{}}"
"{component{1}}"
// ...and properties
"{component'property'}"

// Also components can have named slots...
"{component.slot{}}"
"{component.slot{1}}"
// ...properties...
"{component.property'value'}"
// ...and subcomponents
"{component.subcomponent{other_component}}"

// Component invokation can be added
// in the middle of other strings, forming a list
"some string with {component} in between"

// Lists can be passed as children to components
"{component with some text}"

// As well as named components with component
// list syntax
"{component.subcomponent{# list of strings and {component}s}}"
```

