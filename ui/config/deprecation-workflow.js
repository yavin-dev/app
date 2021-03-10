/* eslint-disable */
self.deprecationWorkflow = self.deprecationWorkflow || {};
self.deprecationWorkflow.config = {
  workflow: [
    { handler: 'silence', matchId: 'ember.globals-resolver' },
    { handler: 'silence', matchId: 'ember-string.htmlsafe-ishtmlsafe' },
    { handler: 'silence', matchId: 'ember-metal.get-with-default' },
    { handler: 'silence', matchId: 'autotracking.mutation-after-consumption' },
    { handler: 'silence', matchId: 'ember-component.send-action' },
    { handler: 'silence', matchId: 'globals-resolver' },
  ],
};
